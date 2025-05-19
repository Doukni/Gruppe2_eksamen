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
        carRepo.save(car);

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("message", "Bil oprettet!");
        model.addAttribute("car", new Car()); // Clear form
        model.addAttribute("cars", carRepo.findAll()); //genstarter automatisk så man ka se bilen

        return "opretBil";
    }
}
