package com.mahayatra.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mahayatra.model.Booking;
import com.mahayatra.model.Bus;
import com.mahayatra.model.Route;
import com.mahayatra.model.User;
import com.mahayatra.repository.BookingRepository;
import com.mahayatra.repository.BusRepository;
import com.mahayatra.repository.RouteRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private RouteRepository routeRepository;


   @GetMapping("/bus/details/{id}")
    public String showBusDetails(@PathVariable Integer id, Model model) {
    Bus bus = busRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bus not found"));

    List<Integer> seats = new ArrayList<>();
    for (int i = 1; i <= bus.getTotalSeats(); i++) {
        seats.add(i);
    }

    model.addAttribute("bus", bus);
    model.addAttribute("seats", seats);
    return "bus-details";
}

    // Handle booking
     @PostMapping("/book-seat")
    public String bookSeat(@RequestParam Integer busId,
                           @RequestParam Integer routeId,
                           @RequestParam String seats,
                           HttpSession session ,
                           RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
        session.setAttribute("redirectAfterLogin", "/bus/details/" + busId);
        return "redirect:/login"; // ✅ redirect to login only if not logged in
    }
        
        
        
        int seatCount = seats.split(",").length;


        Bus bus = busRepository.findById(busId).orElse(null);
        Route route = routeRepository.findById(routeId).orElse(null);
       
        // Get booked seat count
        List<Booking> bookings = bookingRepository.findByRouteIdAndMode(routeId, "BUS");
        int alreadyBooked = bookings.stream().mapToInt(Booking::getSeatCount).sum();

        if (bus.getTotalSeats() - alreadyBooked < seatCount) {
            redirectAttributes.addFlashAttribute("error", "Not enough available seats.");
            return "redirect:/bus-details/" + busId;
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoute(route);
        booking.setMode("BUS");
        booking.setTravelName(bus.getName());
        booking.setTravelDate(LocalDate.now());
        booking.setSeatCount(seatCount);
        booking.setTotalFare(seatCount * bus.getFare());

        bookingRepository.save(booking);

        redirectAttributes.addFlashAttribute("success", "Booking successful!");
        return "redirect:/payment"; // You can change this
    }


   @GetMapping("/payment")
public String showPaymentPage(HttpSession session, Model model) {
    Booking booking = (Booking) session.getAttribute("recentBooking");

    if (booking == null) {
        return "redirect:/index"; // Optional fallback
    }

    model.addAttribute("booking", booking);
    return "payment"; // Loads payment.html
}


}