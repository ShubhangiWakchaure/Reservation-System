package com.mahayatra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahayatra.model.City;

public interface CityRepository extends JpaRepository<City,Integer>{
        City findByNameIgnoreCase(String name);
}
