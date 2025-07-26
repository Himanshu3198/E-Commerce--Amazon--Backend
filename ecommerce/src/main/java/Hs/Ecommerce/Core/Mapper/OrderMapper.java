package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Response.CustomerSummaryDTO;
import Hs.Ecommerce.Core.DTO.Response.OrderItemDTO;
import Hs.Ecommerce.Core.DTO.Response.OrderResponseDTO;
import Hs.Ecommerce.Core.Entity.Order;
import Hs.Ecommerce.Core.Entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                new CustomerSummaryDTO(order.getCustomer().getId(), order.getCustomer().getName()),
                mapOrderItems(order.getOrderItems()),
                order.getTotalAmount(),
                order.getAddress().toString(),
                order.getOrderStatus().name(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    private static List<OrderItemDTO> mapOrderItems(List<OrderItem> items) {
        return items.stream().map(item ->
                new OrderItemDTO(
                        item.getId(),
                        item.getProduct().getProductName(),
                        item.getOrderQuantity(),
                        item.getProduct().getPrice()
                )
        ).collect(Collectors.toList());
    }
}