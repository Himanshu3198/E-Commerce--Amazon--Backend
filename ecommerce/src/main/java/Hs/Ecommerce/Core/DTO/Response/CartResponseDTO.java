package Hs.Ecommerce.Core.DTO.Response;

import java.time.LocalDateTime;
import java.util.List;

public record CartResponseDTO(
        String customerName,
        List<CartItemResponseDTO> cartItems,
        Double discount,
        Double totalAmount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}