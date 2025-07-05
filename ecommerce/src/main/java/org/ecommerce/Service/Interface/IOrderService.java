package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.Address;
import org.ecommerce.Entity.Order;
import org.ecommerce.Enum.OrderStatus;

import java.util.List;

public interface IOrderService{



    Order placeOrder(Long userId);
    Order cancelOrder(Long orderId);
    void updateOrderStatus(long orderId, OrderStatus orderStatus);
    Order getOrderById(Long orderId);
    List<Order> getAllOrder();
    void updateOrder(Order order);
}
