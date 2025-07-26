package Hs.Ecommerce.Core.DTO.Response;

public record OrderItemDTO(
        Long id,
        String productName,
        Integer quantity,
        Double price
) {}