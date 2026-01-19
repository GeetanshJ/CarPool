package car_pooling.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @JsonProperty("departure_time")
    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @JsonProperty("expected_arrival_time")
    @Column(name = "expected_arrival_time", nullable = false)
    private String expectedArrivalTime;

    @JsonProperty("available_seats")
    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getExpectedArrivalTime() { return expectedArrivalTime; }
    public void setExpectedArrivalTime(String expectedArrivalTime) { this.expectedArrivalTime = expectedArrivalTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Rider getRider() { return rider; }
    public void setRider(Rider rider) { this.rider = rider; }
}
