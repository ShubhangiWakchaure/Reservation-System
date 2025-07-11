package com.mahayatra.controller;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mahayatra.model.*;
import com.mahayatra.repository.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired private CityRepository cityRepository;
    @Autowired private RouteRepository routeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private BusRepository busRepository;
    @Autowired private TrainRepository trainRepository;
    @Autowired private BookingRepository bookingRepository;
    

    @GetMapping("")
    public String adminDashboard(HttpSession session) {
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("ADMIN")) {
        return "redirect:/login?error=unauthorized";
    }
    return "admin/admin"; // Loads dashboard HTML
}

    @GetMapping("/cities")
    public String viewCities(Model model){
        model.addAttribute("cities",cityRepository.findAll());
        return "admin/cities";
    }

    @PostMapping("cities/add")
    public String addCity(@RequestParam String name){
        City city=new City();
        city.setName(name);
        cityRepository.save(city);
        return "redirect:/admin/cities";
     }

     @GetMapping("/cities/delete/{id}")
     public String deleteCity(@PathVariable Integer id){
        cityRepository.deleteById(id);
        return "redirect:/admin/cities";
     }


     //--------------route--------------//

     @GetMapping("/routes")
     public String viewRoutes(Model model){
        model.addAttribute("routes",routeRepository.findAll());
        model.addAttribute("cities",cityRepository.findAll());
        return "admin/routes";

     }

     @PostMapping("routes/add")
     public String addRoutes(@RequestParam Integer sourceCityId,@RequestParam Integer destinationCityId){
        Route route =new Route();
        route.setSourceCity(cityRepository.findById(sourceCityId).orElse(null));
        route.setDestinationCity(cityRepository.findById(destinationCityId).orElse(null));
        routeRepository.save(route);
        return "redirect:/admin/routes";
    
     }

     @GetMapping("/routes/delete/{id}")
     public String deleteRoute(@PathVariable int id){
        routeRepository.deleteById(id);
        return "redirect:/admin/routes";
     }

     //------users------//
     @GetMapping("/users")
     public String viewUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "admin/users";
     }

     @GetMapping("/users/delete/{id}")
     public String deleteUsers(@PathVariable int id){
        userRepository.deleteById(id);
        return "redirect:/admin/users";

     }

     //-----buses--------//

     @GetMapping("/buses/add")
     public String viewBus(Model model){
        model.addAttribute("buses",busRepository.findAll());
        model.addAttribute("routes",routeRepository.findAll());
        return "admin/buses";
     }

     @PostMapping("/buses/add")
     public String addBus(@RequestParam String name,
                           @RequestParam String busType,
                           @RequestParam Integer routeId,
                           @RequestParam LocalTime departureTime,
                           @RequestParam LocalTime arrivalTime,
                           @RequestParam Integer totalSeats,
                           @RequestParam Double fare,
                           @RequestParam Integer bookedSeats){
        Bus bus =new Bus(name,busType,routeRepository.findById(routeId).orElse(null),departureTime,arrivalTime,totalSeats,fare,bookedSeats);
        busRepository.save(bus);
        return "redirect:/admin/buses";
     }
      @GetMapping("/buses/delete/{id}")
      public String deleteBus(@PathVariable Integer id){
        trainRepository.deleteById(id);
        return "redirect:/admin/buses";
    }

     @GetMapping("/trains")
     public String viewTrains(Model model){
        model.addAttribute("trains",trainRepository.findAll());
        model.addAttribute("routes",routeRepository.findAll());
        return "admin/trains";
     }

    @PostMapping("/trains/add")
    public String addTrain(@RequestParam String name,
                           @RequestParam String trainType,
                           @RequestParam Integer routeId,
                           @RequestParam LocalTime departureTime,
                           @RequestParam LocalTime arrivalTime,
                           @RequestParam Integer totalSeats,
                           @RequestParam Double fare) {
        Train train = new Train(name, trainType,routeRepository.findById(routeId).orElse(null),
                                departureTime, arrivalTime, totalSeats, fare);
        trainRepository.save(train);
        return "redirect:/admin/trains";
    }

    @GetMapping("/trains/delete/{id}")
    public String deleteTrain(@PathVariable Integer id){
        trainRepository.deleteById(id);
        return "redirect:/admin/trains";
    }

    //-------bookings-----//

    @GetMapping("bookings")
    public String viewBookings(Model model){
        model.addAttribute("bookings",bookingRepository.findAll());
        return "admin/bookings";

    }



}
