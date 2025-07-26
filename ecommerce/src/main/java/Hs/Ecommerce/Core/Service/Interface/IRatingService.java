package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Entity.Rating;

import java.util.List;

public interface IRatingService {

      Rating addRating(Long userId,Long productId,Double rating,String review);
      Rating updateRating(Long ratingId,Double rating,String review);
      List<Rating> findAllReview(Long productId);
}
