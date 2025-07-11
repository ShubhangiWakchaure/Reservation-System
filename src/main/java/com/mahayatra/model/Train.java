package com.mahayatra.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String trainType;
    @ManyToOne
    private Route route;

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Integer totalSeats;
    private Double fare;

    public Train(){}

    public Train(String name,String trainType,Route route,LocalTime departureTime,LocalTime arrivalTime,Integer totalSeats,Double fare){
            this.name = name;
            this.route = route;
            this.trainType = trainType;
            this.departureTime =departureTime;
            this.arrivalTime = arrivalTime;
            this.totalSeats = totalSeats;
            this.fare=fare;
    }
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
     }
    
    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
     public String getTrainType() {
         return trainType;
         }
    public void setTrainType(String trainType) {
         this.trainType = trainType; 
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
}


