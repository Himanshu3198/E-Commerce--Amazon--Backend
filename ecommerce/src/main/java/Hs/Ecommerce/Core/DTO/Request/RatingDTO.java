package Hs.Ecommerce.Core.DTO.Request;

public record RatingDTO(
        Long userId,
        Long productId,
        Double rating,
        String review
) {
}
