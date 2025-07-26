package Hs.Ecommerce.Core.DTO.Response;

public record ProductResponseDTO(
        Long productId,
        String productName,
        String productDescription,
        String productImage,
        String availabilityStatus,
        String productCategory,
        Long productQuantity,
        Double productPrice
) {}
