package com.example.gruppe2_eksamen.controller;


import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateAccountController {


    @Autowired
    private UserRepo userRepo;


    @GetMapping("/createAccount")
    public String createAccountPage() {
        return "createAccount";
    }


    // opretter bruger

    @PostMapping("/createAccount")
    public String createUser(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("username") String username,
            @RequestParam("option") String occupation,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setName(firstName);
        user.setLastName(lastName);
        user.setWorkId(username);
        user.setPassword(password);
        user.setOccupation(getOccupationNameFromOption(occupation));

        userRepo.save(user);

        return "desktop";
    }

    private String getOccupationNameFromOption(String option) {
        return switch (option) {
            case "1" -> "Chef";
            case "2" -> "Skade afdeling";
            case "3" -> "Udlejning";
            case "4" -> "Rapportskriver";
            case "5" -> "Økonom";
            default -> "Ukendt";
        };
    }
}



