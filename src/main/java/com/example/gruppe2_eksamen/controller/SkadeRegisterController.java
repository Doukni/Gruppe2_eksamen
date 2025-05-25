package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Skade;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.SkadeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class SkadeRegisterController {

    @Autowired
    private SkadeRepo skadeRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/skadeRegister")
    public String skadeRegisterPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("skade", new Skade());
        model.addAttribute("cars", carRepo.findByIsReturnedTrue()); // only returned cars


        return "skadeRegister";
    }

    @PostMapping("/skadeRegister")
    public String registrerSkade(@ModelAttribute Skade skade) {
        skade.setRegistrereDato(LocalDate.now());
        skadeRepo.save(skade);
        return "redirect:/skadeRegister";
    }
}
