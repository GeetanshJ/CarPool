package car_pooling.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private String departureTime;
    private String expectedArrivalTime;
    private int availableSeats;
    private double price;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private Rider rider;

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }
 
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
 
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }
 
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getExpectedArrivalTime() {
        return expectedArrivalTime;
    }
 
    public void setExpectedArrivalTime(String expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
 
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }

    public Rider getRider() {
        return rider;
    }
 
    public void setRider(Rider rider) {
        this.rider = rider;
    }
}
