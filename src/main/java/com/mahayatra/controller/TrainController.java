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
import com.mahayatra.model.Route;
import com.mahayatra.model.Train;
import com.mahayatra.model.User;
import com.mahayatra.repository.BookingRepository;
import com.mahayatra.repository.RouteRepository;
import com.mahayatra.repository.TrainRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RouteRepository routeRepository;

    // Show train details with seat layout
    @GetMapping("/train/details/{id}")
    public String showTrainDetails(@PathVariable Integer id, Model model) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Train not found"));

        List<Integer> seats = new ArrayList<>();
        for (int i = 1; i <= train.getTotalSeats(); i++) {
            seats.add(i);
        }

        model.addAttribute("train", train);
        model.addAttribute("seats", seats);
        return "train-details";
    }

    // Handle train booking
    @PostMapping("/book-train-seat")
    public String bookTrainSeat(@RequestParam Integer trainId,
                                @RequestParam Integer routeId,
                                @RequestParam String seats,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            session.setAttribute("redirectAfterLogin", "/train/details/" + trainId);
            return "redirect:/login";
        }

        int seatCount = seats.split(",").length;

        Train train = trainRepository.findById(trainId).orElse(null);
        Route route = routeRepository.findById(routeId).orElse(null);

        // Check seat availability
        List<Booking> bookings = bookingRepository.findByRouteIdAndMode(routeId, "TRAIN");
        int alreadyBooked = bookings.stream().mapToInt(Booking::getSeatCount).sum();

        if (train.getTotalSeats() - alreadyBooked < seatCount) {
            redirectAttributes.addFlashAttribute("error", "Not enough available seats.");
            return "redirect:/train/details/" + trainId;
        }

        // Save booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoute(route);
        booking.setMode("TRAIN");
        booking.setTravelName(train.getName());
        booking.setTravelDate(LocalDate.now());
        booking.setSeatCount(seatCount);
        booking.setTotalFare(seatCount * train.getFare());

        bookingRepository.save(booking);

        session.setAttribute("recentBooking", booking); // Store for payment
        redirectAttributes.addFlashAttribute("success", "Train booking successful!");
        return "redirect:/payment";
    }
}
