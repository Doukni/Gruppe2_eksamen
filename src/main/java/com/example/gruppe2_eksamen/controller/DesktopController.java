package com.example.gruppe2_eksamen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DesktopController {

    @GetMapping("/")// mainpage/desktop så skal man ikke skrive noget i / fx (/desktop)
    public String desktopPage() {
        return "desktop";
    }
}
