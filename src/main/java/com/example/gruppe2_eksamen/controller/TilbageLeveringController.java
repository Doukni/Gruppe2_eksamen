package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.Kunde;
import com.example.gruppe2_eksamen.model.TilbageLevering;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.TilbageleveringRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TilbageLeveringController {

    @Autowired
    private TilbageleveringRepo tilbageleveringRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/tilbagelevering")
    public String showPage(Model model, HttpSession session) {
        Object user = session.getAttribute("loggedUser");
        model.addAttribute("user", user); // OK hvis null

        // Fjern biler med vigtige null-felter
        List<Car> filtreredeBiler = carRepo.findAll().stream()
                .filter(car -> car != null &&
                        car.getBrand() != null &&
                        car.getModel() != null &&
                        car.getLicensePlate() != null)
                .collect(Collectors.toList());

        model.addAttribute("tilbagelevering", new TilbageLevering());
        model.addAttribute("cars", filtreredeBiler);
        model.addAttribute("returns", tilbageleveringRepo.findAll());
        return "tilbagelevering";
    }


    @PostMapping("/tilbagelevering")
    public String processReturn(@ModelAttribute TilbageLevering tilbagelevering) {

        Car partialCar = tilbagelevering.getCar();
        if (partialCar == null || partialCar.getId() == 0) {
            throw new RuntimeException("Ingen bil blev valgt til tilbagelevering");
        }

        Car car = carRepo.findById(partialCar.getId())
                .orElseThrow(() -> new RuntimeException("Bil ikke fundet"));

        Kunde kunde = car.getKunde();

        if (kunde != null && kunde.getDeliveryDate() != null && kunde.getReturnDate() != null) {
            LocalDate start = kunde.getDeliveryDate();
            LocalDate end = kunde.getReturnDate();
            long months = ChronoUnit.MONTHS.between(start, end);
            if (months == 0) months = 1;

            double price = car.getPrice() != null ? car.getPrice() : 0.0;
            double total = months * price;

            tilbagelevering.setReturnDate(end);
            tilbagelevering.setTotalPrice(total);
        }

        tilbagelevering.setCar(car);
        tilbageleveringRepo.save(tilbagelevering);

        car.setAvailability("Klar til afhentning");
        car.setReturned(true);
        carRepo.save(car);

        return "redirect:/tilbagelevering";
    }
}
