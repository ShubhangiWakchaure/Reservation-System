package com.mahayatra.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mahayatra.model.Bus;
import com.mahayatra.model.Route;

public interface BusRepository extends JpaRepository<Bus,Integer>{
    List<Bus>findByRoute(Route route);
}