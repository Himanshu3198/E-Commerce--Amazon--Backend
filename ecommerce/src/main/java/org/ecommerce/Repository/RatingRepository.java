package org.ecommerce.Repository;

import org.ecommerce.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
