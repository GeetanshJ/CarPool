package car_pooling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import car_pooling.models.Ride;
import car_pooling.repository.RideRepository;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> search(String source, String destination) {
        return rideRepository.findBySourceAndDestination(source, destination);
    }
}
