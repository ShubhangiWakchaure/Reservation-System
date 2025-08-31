package com.mahayatra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mahayatra.model.Admin;
import com.mahayatra.model.User;
import com.mahayatra.repository.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    // ✅ Show login page (GET)
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        System.out.println("Login Page Handler");
        return "login"; // loads login.html
    }

    // ✅ Process login form (POST)
  @PostMapping("/login")
    public String login(@RequestParam String username,
                    @RequestParam String password,
                    @RequestParam String role,
                    HttpSession session,
                    Model model) {

    if (role.equals("ADMIN")) {
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin != null) {
            session.setAttribute("loggedInUser", admin);
            session.setAttribute("role", "ADMIN");
            return "redirect:/admin";
        }
    } else if (role.equals("USER")) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            session.setAttribute("role", "USER");

            // ✅ Redirect to original URL (e.g. /account) if saved
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                return "redirect:" + redirectUrl;
            }

            // ✅ Default fallback
            return "redirect:/index";
        }
    }

    model.addAttribute("error", "Invalid credentials or role");
    return "login";
}



    // ✅ Show register page (GET)
    @GetMapping("/register")
    public String registerPage(Model model) {
    model.addAttribute("user", new User());  // ✅ This line is required
    return "register";
}


    //  Process registration form (POST)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
        model.addAttribute("error", "Username already exists");
        return "register";
    }

    userRepository.save(user);
    model.addAttribute("success", "Registration successful. Please login.");
    return "index";
}
     @GetMapping("/bookings")
    public String bookings(HttpSession session) {
        // Check if user is logged in
        Object user = session.getAttribute("user");
        if (user == null) {
            // If NOT logged in → redirect to login page
            return "redirect:/login";
        }

        // If logged in → show bookings page
        return "bookings";
    }

    // ✅ Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
