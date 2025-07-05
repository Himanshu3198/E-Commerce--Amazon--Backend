package Hs.Ecommerce.Core.Repository;

import Hs.Ecommerce.Core.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
