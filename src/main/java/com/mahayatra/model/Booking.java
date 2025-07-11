package com.mahayatra.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Route route;

    private String mode; // "BUS" or "TRAIN"
    private String travelName; // bus or train name
    private LocalDate travelDate;

    private Integer seatCount;
    private Double totalFare;

    // Getters and Setters
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Route getRoute() { return route; }

    public void setRoute(Route route) { this.route = route; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public String getTravelName() { return travelName; }

    public void setTravelName(String travelName) { this.travelName = travelName; }

    public LocalDate getTravelDate() { return travelDate; }

    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }

    public Integer getSeatCount() { return seatCount; }

    public void setSeatCount(Integer seatCount) { this.seatCount = seatCount; }

    public Double getTotalFare() { return totalFare; }

    public void setTotalFare(Double totalFare) { this.totalFare = totalFare; }
}
