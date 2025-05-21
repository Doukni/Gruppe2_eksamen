package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.ForhaandsAftale;
import com.example.gruppe2_eksamen.model.Skade;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.ForhaandsaftaleRepo;
import com.example.gruppe2_eksamen.repository.SkadeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ForhaandsaftaleController {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private SkadeRepo skadeRepo;

    @Autowired
    private ForhaandsaftaleRepo forhaandsaftaleRepo;

    @GetMapping("/forhaandsaftale")
    public String forhaandsaftale(HttpSession session, Model model,
                                  @RequestParam(value = "carId", required = false) Integer carId) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("cars", carRepo.findAll());

        if (carId != null) {
            model.addAttribute("selectedCarId", carId);
            List<Skade> damages = skadeRepo.findByCar_Id(carId);
            model.addAttribute("damages", damages); // Optional: if you want to display damages in the view
        }

        return "forhaandsaftale";
    }

    @PostMapping("/forhaandsaftale")
    public String handleForm(@RequestParam int carId,
                             @RequestParam String buyerName,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate agreementDate,
                             @RequestParam int expectedKm,
                             @RequestParam int actualKm,
                             @RequestParam String currency,
                             @RequestParam double basePrice,
                             @RequestParam String pickupLocation) {

        ForhaandsAftale aftale = new ForhaandsAftale();

        aftale.setBuyerName(buyerName);
        aftale.setAgreementDate(agreementDate);
        aftale.setExpectedKm(expectedKm);
        aftale.setActualKm(actualKm);
        aftale.setCurrency(currency);
        aftale.setPrice(basePrice);
        aftale.setPickupLocation(pickupLocation);

        Car car = carRepo.findById(carId).orElse(null);
        aftale.setCar(car);

        forhaandsaftaleRepo.save(aftale);

        return "redirect:/forhaandsaftale";
    }
}
