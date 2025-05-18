package com.example.gruppe2_eksamen.controller;



import com.example.gruppe2_eksamen.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DesktopController {


    @GetMapping("/")// mainpage/desktop så skal man ikke skrive noget i / fx (/desktop)
    public String desktopPage(HttpSession session, Model model ) {


        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        return "desktop";
    }

}
