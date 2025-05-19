package com.example.gruppe2_eksamen.controller;


import com.example.gruppe2_eksamen.model.Rapport;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.RapportRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RapportController {

    @Autowired
    private RapportRepo rapportRepo;

    @GetMapping("/rapport")
    public String rapportPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("rapporter", rapportRepo.findAll());
        model.addAttribute("rapport", new Rapport());
        return "rapport";
    }

    @PostMapping("/rapport")
    public String rapportPost(@ModelAttribute Rapport rapport, HttpSession session) {
        rapportRepo.save(rapport);
        return "redirect:/rapport";
    }

    @GetMapping("/rapporter")
    public String visAlleRapporter(HttpSession session, Model model) {
        model.addAttribute("rapporter", rapportRepo.findAll());
        return "rapport-oversigt";
    }



}
