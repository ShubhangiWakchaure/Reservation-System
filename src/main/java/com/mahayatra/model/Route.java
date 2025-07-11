package com.mahayatra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private City sourceCity;

    @ManyToOne
    private City destinationCity;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public City getSourceCity(){
        return sourceCity;
    }

    public void setSourceCity(City sourceCity){
        this.sourceCity = sourceCity;
    }

    public City getDestinationCity(){
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity){
        this.destinationCity = destinationCity;
    }




}
