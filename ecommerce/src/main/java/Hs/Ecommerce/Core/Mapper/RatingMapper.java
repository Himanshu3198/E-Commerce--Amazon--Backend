package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.DTO.Request.RatingDTO;
import Hs.Ecommerce.Core.DTO.Response.ProductSummaryDTO;
import Hs.Ecommerce.Core.DTO.Response.RatingResponseDTO;
import Hs.Ecommerce.Core.DTO.Response.UserSummaryDTO;
import Hs.Ecommerce.Core.Entity.Rating;


public class RatingMapper {

    public static RatingResponseDTO toDTO(Rating rating) {
        return new RatingResponseDTO(
                rating.getId(),
                new ProductSummaryDTO(rating.getProduct().getId(), rating.getProduct().getProductName()),
                new UserSummaryDTO(rating.getUser().getId(), rating.getUser().getName()),
                rating.getRating(),
                rating.getReview()
        );
    }
}