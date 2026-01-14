package car_pooling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import car_pooling.models.Ride;
import car_pooling.services.RideService;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/create")
    public Ride create(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @GetMapping("/search")
    public List<Ride> search(@RequestParam String source,
                             @RequestParam String destination) {
        return rideService.search(source, destination);
    }
}
