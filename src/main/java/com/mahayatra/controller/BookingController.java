package com.mahayatra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {
    @GetMapping("/bookings")
    public String Bookings(HttpSession session){
        // Check if the user is logged in
        Object loggedInUser = session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            // Save redirect URL in session
            session.setAttribute("redirectAfterLogin", "/bookings");

            // If NOT logged in → redirect to login page
            return "redirect:/login";
        }

        // If logged in → show bookings page
        return "bookings"; // bookings.html

    }
    
}
