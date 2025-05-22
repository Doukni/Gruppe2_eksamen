package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

@Controller
public class DesktopController {

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/")
    public String desktopPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        model.addAttribute("cars", carRepo.findAll());

        double totalPrice = StreamSupport.stream(carRepo.findAll().spliterator(), false)
                .filter(car -> "Udlejet".equalsIgnoreCase(car.getAvailability()))
                .mapToDouble(car -> car.getPrice() != null ? car.getPrice() : 0)
                .sum();

        model.addAttribute("totalRentedPrice", totalPrice);

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        return "desktop";
    }
}