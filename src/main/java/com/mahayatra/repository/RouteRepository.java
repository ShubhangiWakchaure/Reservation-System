package com.mahayatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahayatra.model.City;
import com.mahayatra.model.Route;

public interface RouteRepository extends JpaRepository<Route,Integer> {
    Route findBySourceCityAndDestinationCity(City sourceCity, City destinationCity);
}
