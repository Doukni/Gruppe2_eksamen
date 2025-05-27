package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.*;
import com.example.gruppe2_eksamen.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/desktop")
public class DesktopController {

    @Autowired
    private CarRepo carRepo;
    @Autowired
    private TilbageleveringRepo tilbageleveringRepo;
    @Autowired
    private RapportRepo rapportRepo;
    @Autowired
    private SkadeRepo skadeRepo;
    @Autowired
    private ForhaandsaftaleRepo forhaandsaftaleRepo;

    @GetMapping
    public String desktopPage(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        model.addAttribute("cars", carRepo.findAll());

        double totalPrice = StreamSupport.stream(carRepo.findAll().spliterator(), false)
                .filter(car -> "Udlejet".equalsIgnoreCase(car.getAvailability()))
                .mapToDouble(car -> car.getPrice() != null ? car.getPrice() : 0)
                .sum();

        long antalUdlejede = StreamSupport.stream(carRepo.findAll().spliterator(), false)
                .filter(car -> "Udlejet".equalsIgnoreCase(car.getAvailability()))
                .count();

        model.addAttribute("totalRentedPrice", totalPrice);
        model.addAttribute("antalUdlejede", antalUdlejede);

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        return "desktop";
    }

    @PostMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable int id) {
        var optionalCar = carRepo.findById(id);
        if (optionalCar.isPresent()) {
            var car = optionalCar.get();

            // sletter alle links fra bilen så den ikke er knyttet til noget
            //andet i databasen som kunder osv
            if (car.getKunde() != null) {
                car.getKunde().setCar(null);
                car.setKunde(null);
            }

            List<TilbageLevering> leveringer = tilbageleveringRepo.findByCar(car);
            for (TilbageLevering levering : leveringer) {
                tilbageleveringRepo.delete(levering);
            }

            List<Rapport> rapporter = rapportRepo.findByCar(car);
            for (Rapport rapport : rapporter) {
                rapport.setCar(null);
            }
            rapportRepo.saveAll(rapporter);

            List<Skade> skader = skadeRepo.findByCar(car);
            for (Skade skade : skader) {
                skade.setCar(null);
            }
            skadeRepo.saveAll(skader);

            List<ForhaandsAftale> aftaler = forhaandsaftaleRepo.findByCar(car);
            for (ForhaandsAftale aftale : aftaler) {
                aftale.setCar(null);
            }
            forhaandsaftaleRepo.saveAll(aftaler);

            carRepo.delete(car);
        }
        return "redirect:/desktop";
    }
}
