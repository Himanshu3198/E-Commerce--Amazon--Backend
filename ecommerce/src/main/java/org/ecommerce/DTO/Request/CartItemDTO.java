package org.ecommerce.DTO.Request;

public record CartItemDTO(
        Long productId,
        Long quantity
) {}
