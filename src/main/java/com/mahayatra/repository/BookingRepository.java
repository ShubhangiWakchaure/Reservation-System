package com.mahayatra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahayatra.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking>findByRouteIdAndMode(Integer id,String mode);

}
