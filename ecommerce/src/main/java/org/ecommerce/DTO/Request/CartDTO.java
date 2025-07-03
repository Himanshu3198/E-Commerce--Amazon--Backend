package org.ecommerce.DTO.Request;

import org.ecommerce.Entity.CartItem;

import java.util.List;

public record CartDTO(
        Long userId,
        List<CartItemDTO> cartItems,
        Double totalAmount,
        Double discount
) {}
