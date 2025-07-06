package Hs.Ecommerce.Core.Mapper;

import Hs.Ecommerce.Core.Entity.Rating;

import java.util.HashMap;
import java.util.Map;

public class RatingMapper {

    public static Map<String,Object> toDTO(Rating rating){
        Map<String,Object> response = new HashMap<>();
        response.put("ratingId",rating.getId());
        response.put("product",rating.getProduct());
        response.put("submitBy",rating.getUser());
        response.put("rating",rating.getRating());
        response.put("review",rating.getReview());
        return response;
    }
}
