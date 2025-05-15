package com.example.gruppe2_eksamen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateAccountController {


    @GetMapping("/createAccount")
    public String createAccountPage() {
        return "createAccount";
    }
}
