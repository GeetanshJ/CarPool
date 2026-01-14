package car_pooling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import car_pooling.models.Rider;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}

