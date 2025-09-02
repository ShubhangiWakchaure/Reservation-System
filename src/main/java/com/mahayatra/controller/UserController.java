package com.mahayatra.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mahayatra.model.User;
import com.mahayatra.repository.UserRepository;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {
   @Autowired
    private UserRepository userRepository;
  

   
 @GetMapping("/account")
  public String showAccountPage(HttpSession session, Model model) {
    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        session.setAttribute("redirectAfterLogin", "/account");
        return "redirect:/login";
    }

    model.addAttribute("user", user);
    return "account";
}

  @GetMapping("/about")
  public String aboutPage() {
    return "about";
}
  @GetMapping("/offers")
  public String offersPage() {
    return "offers";
  }
   @GetMapping("/help")
  public String helpPage() {
    return "help";
  }
  @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    model.addAttribute("user", user);
    return "profile"; // returns profile.html
    }
    

}
