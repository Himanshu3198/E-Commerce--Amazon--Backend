package org.ecommerce.Service.Implementation;


import org.ecommerce.Entity.Order;
import org.ecommerce.Enum.OrderStatus;
import org.ecommerce.Enum.PaymentMethod;
import org.ecommerce.Exception.PaymentFailedException;
import org.ecommerce.Exception.ResourceNotFoundException;
import org.ecommerce.Payment.PaymentFactory;
import org.ecommerce.Payment.PaymentStrategy;
import org.ecommerce.Service.Interface.ICheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckoutServiceImp implements ICheckoutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutServiceImp.class);
    private final OrderServiceImp orderServiceImp;
    private final ProductServiceImp productServiceImp;

    public CheckoutServiceImp(OrderServiceImp orderServiceImp, ProductServiceImp productServiceImp){
        this.orderServiceImp = orderServiceImp;
        this.productServiceImp = productServiceImp;
    }


    @Override
    public void checkout(Long orderId, PaymentMethod paymentMethod) {

        try {
            Order order = orderServiceImp.getOrderById(orderId);
            if(order == null){
                throw new ResourceNotFoundException("Cannot find the order with id: "+orderId);
            }
            PaymentStrategy paymentStrategy = PaymentFactory.getPaymentStrategy(paymentMethod);
            Boolean payment =paymentStrategy.pay(String.valueOf(orderId),order.getTotalAmount(),order.getCustomer());

            if(!payment){
                order.setOrderStatus(OrderStatus.FAILED);
                order.setUpdatedAt(LocalDateTime.now());
                orderServiceImp.updateOrder(order);
                throw new PaymentFailedException("Payment has been failed for orderId: "+orderId);
            }
            order.setOrderStatus(OrderStatus.DELIVERED);
            order.setUpdatedAt(LocalDateTime.now());
            orderServiceImp.updateOrder(order);

        } catch (DataAccessException dae) {
            logAndThrow("Database error occurred while checkout", dae);
        }catch (Exception ex) {
            logAndThrow("Unexpected error occurred while checkout", ex);
        }
    }

    private void logAndThrow(String message, Exception ex) {
        LOGGER.error("{}: {}", message, ex.getMessage(), ex);
        throw new RuntimeException(message);
    }

    private void updateInventory(Order order){

    }
}
