package car_pooling.models;

import jakarta.persistence.*;

@Entity
@Table(name = "driver_details")
public class DriverDetails {

    @Id
    private int driverId;

    private String licenseNo;
    private boolean verified;
    private double rating;

    @OneToOne
    @MapsId
    @JoinColumn(name = "driver_id")
    private User user;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
