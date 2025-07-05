package Hs.Ecommerce.Core.Repository;

import Hs.Ecommerce.Core.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
