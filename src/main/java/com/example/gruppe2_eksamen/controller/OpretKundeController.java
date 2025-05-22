package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Kunde;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.KundeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class OpretKundeController {

    @Autowired
    private KundeRepo kundeRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/opretKunde")
    public String opretKunde(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("kunde", new Kunde());
        model.addAttribute("kunder", kundeRepo.findAll());
        model.addAttribute("cars", carRepo.findByAvailability("Tilgængelig"));

        return "opretKunde";
    }

    @PostMapping("/opretKunde")
    public String opretKunde(@ModelAttribute Kunde kunde,
                             @RequestParam("carId") int carId,
                             Model model) {

        // Tjek kredit og betaling
        if (!kunde.getKreditgodkendt() || !kunde.getBetaltForsteYdelse()) {
            model.addAttribute("urlError", "Kunden er ikke kreditgodkendt eller mangler betaling.");
            model.addAttribute("kunde", kunde);
            model.addAttribute("kunder", kundeRepo.findAll());
            model.addAttribute("cars", carRepo.findByAvailability("Tilgængelig"));
            return "opretKunde";
        }

        // Sæt leveringsdato
        LocalDate deliveryDate = LocalDate.now();
        kunde.setDeliveryDate(deliveryDate);

        if ("Limited".equalsIgnoreCase(kunde.getLimitedOrUnlimited())) {
            kunde.setReturnDate(deliveryDate.plusDays(150));
        } else if ("Unlimited".equalsIgnoreCase(kunde.getLimitedOrUnlimited())) {
            kunde.setReturnDate(deliveryDate.plusMonths(3));
        }

        var carOptional = carRepo.findById(carId);
        if (carOptional.isPresent()) {
            var car = carOptional.get();

            if (!"Tilgængelig".equalsIgnoreCase(car.getAvailability())) {
                model.addAttribute("urlError", "Bilen er ikke længere tilgængelig.");
                model.addAttribute("kunde", kunde);
                model.addAttribute("kunder", kundeRepo.findAll());
                model.addAttribute("cars", carRepo.findByAvailability("Tilgængelig"));
                return "opretKunde";
            }

            car.setAvailability("Udlejet");
            carRepo.save(car);
            kunde.setCar(car);
        } else {
            model.addAttribute("urlError", "Bilen blev ikke fundet.");
            model.addAttribute("kunde", kunde);
            model.addAttribute("kunder", kundeRepo.findAll());
            model.addAttribute("cars", carRepo.findByAvailability("Tilgængelig"));
            return "opretKunde";
        }

        try {
            kundeRepo.save(kunde);
        } catch (Exception e) {
            model.addAttribute("urlError", "Kunne ikke oprette kunde: Email eller kørekortnummer er allerede i brug.");
            model.addAttribute("kunde", kunde);
            model.addAttribute("kunder", kundeRepo.findAll());
            model.addAttribute("cars", carRepo.findByAvailability("Tilgængelig"));
            return "opretKunde";
        }

        return "redirect:/";
    }
}