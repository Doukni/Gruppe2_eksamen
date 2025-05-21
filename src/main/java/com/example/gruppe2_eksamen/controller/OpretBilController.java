package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OpretBilController {

    private final CarRepo carRepo;

    @Autowired
    public OpretBilController(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @GetMapping("/opretBil")
    public String opretBilerPage(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("car", new Car());
        model.addAttribute("cars", carRepo.findAll());

        return "opretBil";
    }

    @PostMapping("/opretBil")
    public String createCar(@ModelAttribute Car car, HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        // Billed-URL-validering
        String imageUrl = car.getCarImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                java.net.URL url = new java.net.URL(imageUrl);
                java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);

                int responseCode = connection.getResponseCode();
                if (responseCode < 200 || responseCode >= 400) {
                    model.addAttribute("urlError", "Billed-URL virker ikke eller findes ikke.");
                    model.addAttribute("car", car);
                    model.addAttribute("cars", carRepo.findAll());
                    return "opretBil";
                }
            } catch (Exception e) {
                model.addAttribute("urlError", "Ugyldig billede-URL.");
                model.addAttribute("car", car);
                model.addAttribute("cars", carRepo.findAll());
                return "opretBil";
            }
        }



        carRepo.save(car);
        model.addAttribute("message", "Bil oprettet!");
        model.addAttribute("car", new Car()); // Clear form
        model.addAttribute("cars", carRepo.findAll()); // Genopfrisk liste

        return "opretBil";
    }
}
