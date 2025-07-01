package org.ecommerce.Service.Interface;

import org.ecommerce.Entity.Order;
import org.ecommerce.Enum.OrderStatus;

public class IOrderService {

    Order placeOrder(Long userId);
    Order cancelOrder(Long orderId);
    void updateOrderStatus(long orderId, OrderStatus orderStatus);
    Order getOrderById(Long orderId);
    Order getAllOrder();
}
