package com.mahayatra.controller;

import com.mahayatra.model.*;
import com.mahayatra.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SearchController {

    @Autowired private CityRepository cityRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private BusRepository busRepository;
    @Autowired private TrainRepository trainRepository;

    @PostMapping("/search")
    public String search(@RequestParam String sourceCity,
                         @RequestParam String destinationCity,
                         @RequestParam String mode,
                         @RequestParam LocalDate date,
                         Model model) {

        City source = cityRepository.findByNameIgnoreCase(sourceCity.trim());
        City destination = cityRepository.findByNameIgnoreCase(destinationCity.trim());

        if (source == null || destination == null) {
            model.addAttribute("message", "Invalid city name entered.");
            return "no-results";
        }

        Route route = routeRepository.findBySourceCityAndDestinationCity(source, destination);

        if (route == null) {
            model.addAttribute("message", "No route found between selected cities.");
            return "no-results";
        }

        model.addAttribute("route", route);
        model.addAttribute("date", date);
        model.addAttribute("mode", mode);

        if (mode.equalsIgnoreCase("BUS")) {
            List<Bus> buses = busRepository.findByRoute(route);
            model.addAttribute("buses", buses);
            return "bus";
        } else {
            List<Train> trains = trainRepository.findByRoute(route);
            model.addAttribute("trains", trains);
            return "train";
        }
    }
}
