package car_pooling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import car_pooling.models.Ride;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySourceAndDestination(String source, String destination);
}
