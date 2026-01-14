package car_pooling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import car_pooling.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
