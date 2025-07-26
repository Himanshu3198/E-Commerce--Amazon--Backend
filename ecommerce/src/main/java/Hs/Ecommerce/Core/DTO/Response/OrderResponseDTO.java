package Hs.Ecommerce.Core.DTO.Response;

import Hs.Ecommerce.Core.Entity.Address;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long orderId,
        CustomerSummaryDTO customer,
        List<OrderItemDTO> orderItems,
        Double totalAmount,
        String customerAddress,
        String orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}