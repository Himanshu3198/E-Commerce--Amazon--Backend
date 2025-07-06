package Hs.Ecommerce.Core.Repository;

import Hs.Ecommerce.Core.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByUser_Id(Long userId);
}
