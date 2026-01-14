package car_pooling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import car_pooling.models.Booking;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
}
