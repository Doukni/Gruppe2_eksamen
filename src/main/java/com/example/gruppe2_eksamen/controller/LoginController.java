package com.example.gruppe2_eksamen.controller;


import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // bruger login

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userRepo.findByWorkId(username);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedUser", user);
            return "redirect:/";
        } else {
            return "login";
        }
    }
}
