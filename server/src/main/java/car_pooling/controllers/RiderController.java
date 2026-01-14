package car_pooling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import car_pooling.models.Rider;
import car_pooling.services.RiderService;

@RestController
@RequestMapping("/riders")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/become")
    public Rider become(@RequestBody Rider rider) {
        return riderService.becomeRider(rider);
    }
}
