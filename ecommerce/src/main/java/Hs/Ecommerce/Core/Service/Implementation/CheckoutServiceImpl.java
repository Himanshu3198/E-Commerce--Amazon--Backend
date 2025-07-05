package Hs.Ecommerce.Core.Service.Implementation;

import Hs.Ecommerce.Core.Entity.Order;
import Hs.Ecommerce.Core.Entity.OrderItem;
import Hs.Ecommerce.Core.Enum.OrderStatus;
import Hs.Ecommerce.Core.Enum.PaymentMethod;
import Hs.Ecommerce.Core.Exception.CheckoutException;
import Hs.Ecommerce.Core.Exception.PaymentFailedException;
import Hs.Ecommerce.Core.Exception.ResourceNotFoundException;
import Hs.Ecommerce.Core.Exception.UserCartException;
import Hs.Ecommerce.Core.Payment.PaymentFactory;
import Hs.Ecommerce.Core.Payment.PaymentStrategy;
import Hs.Ecommerce.Core.Service.Interface.ICartService;
import Hs.Ecommerce.Core.Service.Interface.ICheckoutService;
import Hs.Ecommerce.Core.Service.Interface.IInventoryManager;
import Hs.Ecommerce.Core.Service.Interface.IOrderService;
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
    private final ICartService cartService;

    public CheckoutServiceImpl(IOrderService orderService, IInventoryManager inventoryManager,ICartService cartService) {
        this.orderService = orderService;
        this.inventoryManager = inventoryManager;
        this.cartService = cartService;
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
//          update the inventory to deduct the product quantity
            updateInventory(order);
//          clear the cart once order is placed
            cartService.clearCart(order.getCustomer().getId());

        } catch (PaymentFailedException | ResourceNotFoundException  | UserCartException ex) {
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
