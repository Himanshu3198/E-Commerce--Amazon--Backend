package Hs.Ecommerce.Core.DTO.Response;

public record CartItemResponseDTO(
        String productName,
        Double price,
        int quantity
) {
}
