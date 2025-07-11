package com.mahayatra.model;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String busType;

    @ManyToOne
    private Route route;

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer totalSeats;
    private Double fare;

    @Column(nullable = false)
    private Integer bookedSeats = 0; // Default 0 when bus is added


    public Bus() {}

    public Bus(String name, String busType, Route route,
               LocalTime departureTime, LocalTime arrivalTime,
               Integer totalSeats, Double fare,Integer bookedSeats) {
        this.name = name;
        this.busType = busType;
        this.route = route;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.fare = fare;
        this.bookedSeats=bookedSeats;
    }

    // Getters and Setters
    public Integer getId() { 
        return id; 
    }

    public void setId(Integer id) { 
        this.id = id; 
     }

    public String getName() {
         return name; 
        }
    public void setName(String name) {
         this.name = name; 
        }

    public String getBusType() {
         return busType;
         }
    public void setBusType(String busType) {
         this.busType = busType; 
        }

    public Route getRoute() { 
        return route; 
    }
    public void setRoute(Route route) {
         this.route = route; 
        }

    public LocalTime getDepartureTime() {
         return departureTime;
         }
    public void setDepartureTime(LocalTime departureTime) {
         this.departureTime = departureTime;
         }

    public LocalTime getArrivalTime() {
         return arrivalTime; 
        }
    public void setArrivalTime(LocalTime arrivalTime) {
         this.arrivalTime = arrivalTime; 
        }

    public Integer getTotalSeats() {
         return totalSeats; 
        }
    public void setTotalSeats(Integer totalSeats) {
         this.totalSeats = totalSeats;
         }

    public Double getFare() {
         return fare; 
        }
    public void setFare(Double fare) { 
        this.fare = fare; 
    }

    public Integer getBookedSeats(){
        return bookedSeats;
    }

    public void setBookedSeats(Integer bookedSeats){
        this.bookedSeats=bookedSeats;
    }
}
