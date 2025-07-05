package Hs.Ecommerce.Core.Repository;

import Hs.Ecommerce.Core.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
