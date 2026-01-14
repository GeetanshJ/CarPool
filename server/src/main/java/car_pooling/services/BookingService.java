package car_pooling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import car_pooling.models.Booking;
import car_pooling.models.Ride;
import car_pooling.models.User;
import car_pooling.repository.BookingRepository;
import car_pooling.repository.RideRepository;
import car_pooling.repository.UserRepository;

import java.util.List;

@Service
public class BookingService {

    @Autowired  
    private BookingRepository bookingRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Booking bookRide(Booking booking) {

        Ride ride = rideRepository.findById(
                booking.getRide().getId()
        ).orElseThrow();

        if (ride.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        User user = userRepository.findById(
                booking.getUser().getId()
        ).orElseThrow();

        booking.setRide(ride);
        booking.setUser(user);
        booking.setStatus("BOOKED");

        ride.setAvailableSeats(ride.getAvailableSeats() - 1);

        rideRepository.save(ride);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking cancel(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Ride ride = booking.getRide();

        ride.setAvailableSeats(ride.getAvailableSeats() + 1);
        booking.setStatus("CANCELLED");

        rideRepository.save(ride);
        return bookingRepository.save(booking);
    }

    public List<Booking> history(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
