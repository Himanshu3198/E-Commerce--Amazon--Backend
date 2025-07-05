package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.Entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderMapper {

    public static Map<String,Object> toDTO(Order order){
        Map<String,Object> response = new HashMap<>();
        response.put("orderId",order.getId());
        response.put("customerName",order.getCustomer().getName());
        response.put("orderItem",order.getOrderItems().toString());
        response.put("totalAmount",order.getTotalAmount());
        response.put("customerAddress",order.getAddress());
        response.put("orderStatus",order.getOrderStatus());
        response.put("createdAt",order.getCreatedAt());
        response.put("updatedAt",order.getUpdatedAt());
        return  response;
    }
}
