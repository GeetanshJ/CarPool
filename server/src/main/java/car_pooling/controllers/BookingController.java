package car_pooling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import car_pooling.models.Booking;
import car_pooling.services.BookingService;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public Booking book(@RequestBody Booking booking) {
        return bookingService.bookRide(booking);
    }

    @PostMapping("/cancel/{id}")
    public Booking cancel(@PathVariable Long id) {
        return bookingService.cancel(id);
    }

    @GetMapping("/history/{userId}")
    public List<Booking> history(@PathVariable Long userId) {
        return bookingService.history(userId);
    }
}
