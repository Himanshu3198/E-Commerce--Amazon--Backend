package org.ecommerce.DTO.Request;


public record OrderItemDTO(
        Long productId,
        Long orderQuantity
) {}
