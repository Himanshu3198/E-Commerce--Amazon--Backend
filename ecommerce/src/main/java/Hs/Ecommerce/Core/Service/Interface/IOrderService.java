package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Entity.Order;
import Hs.Ecommerce.Core.Enum.OrderStatus;

import java.util.List;

public interface IOrderService{



    Order placeOrder(Long userId);
    Order cancelOrder(Long orderId);
    void updateOrderStatus(long orderId, OrderStatus orderStatus);
    Order getOrderById(Long orderId);
    List<Order> getAllOrder();
    void updateOrder(Order order);
}
