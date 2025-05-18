package com.example.gruppe2_eksamen.controller;


import com.example.gruppe2_eksamen.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RapportController {

    @GetMapping("/rapport")
    public String rapportPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }
        return "rapport";
    }

}
