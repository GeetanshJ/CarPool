package car_pooling.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import car_pooling.models.*;
import car_pooling.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BookingServiceTest {

    @Mock BookingRepository bookingRepository;
    @Mock RideRepository rideRepository;
    @Mock UserRepository userRepository;

    @InjectMocks BookingService bookingService;

    @Test
    void book_success() {
        Ride r = new Ride(); r.setAvailableSeats(2);
        User u = new User();
        Booking b = new Booking(); b.setRide(r); b.setUser(u);

        when(rideRepository.findById(any())).thenReturn(Optional.of(r));
        when(userRepository.findById(any())).thenReturn(Optional.of(u));
        when(bookingRepository.save(any())).thenReturn(b);

        assertNotNull(bookingService.bookRide(b));
    }

    @Test
    void book_no_seats() {
        Ride r = new Ride(); r.setAvailableSeats(0);
        Booking b = new Booking(); b.setRide(r);

        when(rideRepository.findById(any())).thenReturn(Optional.of(r));
        assertThrows(RuntimeException.class, () -> bookingService.bookRide(b));
    }

    @Test
    void cancel_success() {
        Ride r = new Ride(); r.setAvailableSeats(1);
        Booking b = new Booking(); b.setRide(r);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(b));
        when(bookingRepository.save(any())).thenReturn(b);

        assertEquals("CANCELLED", bookingService.cancel(1L).getStatus());
    }

    @Test
    void cancel_invalid() {
        when(bookingRepository.findById(9L)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> bookingService.cancel(9L));
    }

    @Test
    void history_test() {
        when(bookingRepository.findByUserId(1L)).thenReturn(List.of(new Booking()));
        assertEquals(1, bookingService.history(1L).size());
    }
}
