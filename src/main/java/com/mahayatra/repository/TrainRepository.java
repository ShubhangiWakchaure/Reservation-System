package com.mahayatra.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahayatra.model.Route;
import com.mahayatra.model.Train;

public interface TrainRepository extends JpaRepository<Train,Integer>{
    List<Train>findByRoute(Route route);
}

