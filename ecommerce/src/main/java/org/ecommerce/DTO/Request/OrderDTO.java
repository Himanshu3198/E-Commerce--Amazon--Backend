package org.ecommerce.DTO.Request;

import org.ecommerce.Enum.OrderStatus;

import java.util.List;

public record OrderDTO(
        Long id,
        UserDTO customer,
        List<OrderItemDTO> orderItems,
        Double totalAmount,
        AddressDTO address,
        OrderStatus orderStatus
) {}