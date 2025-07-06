package Hs.Ecommerce.Core.Repository;

import Hs.Ecommerce.Core.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> getAllRatingByProductId(Long productId);
}
