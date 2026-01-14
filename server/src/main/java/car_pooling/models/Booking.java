package car_pooling.models;

import jakarta.persistence.*;
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingTime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingTime() {
        return bookingTime;
    }
 
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }

    public Ride getRide() {
        return ride;
    }
 
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
}
