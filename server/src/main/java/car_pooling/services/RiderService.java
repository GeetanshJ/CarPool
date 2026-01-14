package car_pooling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import car_pooling.models.Rider;
import car_pooling.repository.RiderRepository;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    public Rider becomeRider(Rider rider) {
        return riderRepository.save(rider);
    }
}
