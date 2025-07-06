package Hs.Ecommerce.Core.Service.Interface;

import Hs.Ecommerce.Core.Entity.Rating;

import java.util.List;

public interface IRatingService {

      Rating addRating(Long userId,Long productId,Long rating,String review);
      Rating updateRating(Long ratingId,Long rating,String review);
      List<Rating> findAllReview(Long productId);
}
