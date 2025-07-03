package org.ecommerce.Service.Implementation;

import org.ecommerce.Entity.Order;
import org.ecommerce.Entity.OrderItem;
import org.ecommerce.Enum.OrderStatus;
import org.ecommerce.Enum.PaymentMethod;
import org.ecommerce.Exception.CheckoutException;
import org.ecommerce.Exception.PaymentFailedException;
import org.ecommerce.Exception.ResourceNotFoundException;
import org.ecommerce.Payment.PaymentFactory;
import org.ecommerce.Payment.PaymentStrategy;
import org.ecommerce.Service.Interface.ICheckoutService;
import org.ecommerce.Service.Interface.IInventoryManager;
import org.ecommerce.Service.Interface.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutServiceImpl.class);
    private final IOrderService orderService;
    private final IInventoryManager inventoryManager;

    public CheckoutServiceImpl(IOrderService orderService, IInventoryManager inventoryManager) {
        this.orderService = orderService;
        this.inventoryManager = inventoryManager;
    }

    @Override
    public void checkout(Long orderId, PaymentMethod paymentMethod) {
        LOGGER.info("Starting checkout for orderId: {}", orderId);

        try {
            Order order = orderService.getOrderById(orderId);

            PaymentStrategy paymentStrategy = PaymentFactory.getPaymentStrategy(paymentMethod);
            boolean paymentSuccess = paymentStrategy.pay(String.valueOf(orderId), order.getTotalAmount(), order.getCustomer());

            if (!paymentSuccess) {
                updateOrderStatus(order, OrderStatus.FAILED);
                throw new PaymentFailedException("Payment failed for orderId: " + orderId);
            }

            updateOrderStatus(order, OrderStatus.DELIVERED);
            updateInventory(order);

        } catch (PaymentFailedException | ResourceNotFoundException ex) {
            LOGGER.warn("Checkout failed: {}", ex.getMessage(), ex);
            throw ex;
        } catch (DataAccessException dae) {
            LOGGER.error("Database error during checkout for orderId {}: {}", orderId, dae.getMessage(), dae);
            throw new CheckoutException("Database error during checkout");
        } catch (Exception ex) {
            LOGGER.error("Unexpected error during checkout for orderId {}: {}", orderId, ex.getMessage(), ex);
            throw new CheckoutException("Unexpected error during checkout");
        }
    }

    private void updateOrderStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderService.updateOrder(order);
        LOGGER.info("Order status updated to {} for orderId: {}", status, order.getId());
    }

    private void updateInventory(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems == null || orderItems.isEmpty()) {
            throw new CheckoutException("OrderItems cannot be null or empty for orderId: " + order.getId());
        }

        inventoryManager.updateProductByOrder(orderItems);
        LOGGER.info("Inventory updated for orderId: {}", order.getId());
    }
}
