package car_pooling.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import car_pooling.models.Ride;
import car_pooling.repository.RideRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RideServiceTest {

    @Mock
    RideRepository rideRepository;

    @InjectMocks
    RideService rideService;

    @Test
    void create_ride_success() {
        Ride r = new Ride();
        when(rideRepository.save(r)).thenReturn(r);
        assertNotNull(rideService.createRide(r));
    }

    @Test
    void search_rides_found() {
        when(rideRepository.findBySourceAndDestination("A","B"))
                .thenReturn(List.of(new Ride()));
        assertEquals(1, rideService.search("A","B").size());
    }

    @Test
    void search_rides_empty() {
        when(rideRepository.findBySourceAndDestination("X","Y"))
                .thenReturn(List.of());
        assertTrue(rideService.search("X","Y").isEmpty());
    }

    @Test
    void save_called() {
        Ride r = new Ride();
        rideService.createRide(r);
        verify(rideRepository).save(r);
    }

    @Test
    void null_search() {
        when(rideRepository.findBySourceAndDestination(null,null))
                .thenReturn(List.of());
        assertTrue(rideService.search(null,null).isEmpty());
    }
}
