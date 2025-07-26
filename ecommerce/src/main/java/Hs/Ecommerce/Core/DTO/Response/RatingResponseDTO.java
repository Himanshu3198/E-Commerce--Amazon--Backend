package Hs.Ecommerce.Core.DTO.Response;

import Hs.Ecommerce.Core.Entity.Product;
import Hs.Ecommerce.Core.Entity.User;

public record RatingResponseDTO(
        Long ratingId,
        ProductSummaryDTO product,
        UserSummaryDTO submitBy,
        Double rating,
        String review
) {}
