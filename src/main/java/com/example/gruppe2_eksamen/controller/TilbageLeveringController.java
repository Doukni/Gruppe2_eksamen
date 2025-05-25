package com.example.gruppe2_eksamen.controller;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.Kunde;
import com.example.gruppe2_eksamen.model.TilbageLevering;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.TilbageLeveringRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
public class TilbageLeveringController {


    @Autowired
    private TilbageLeveringRepo tilbageleveringRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping("/tilbagelevering")
    public String showPage(Model model, HttpSession session) {
        model.addAttribute("tilbagelevering", new TilbageLevering());
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("returns", tilbageleveringRepo.findAll());
        model.addAttribute("user", session.getAttribute("loggedUser"));
        return "tilbagelevering";
    }

    @PostMapping("/tilbagelevering")
    public String processReturn(@ModelAttribute TilbageLevering tilbagelevering) {

        Car partialCar = tilbagelevering.getCar();


        Car car = carRepo.findById(partialCar.getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Kunde kunde = car.getKunde();

        if (kunde != null && kunde.getDeliveryDate() != null && kunde.getReturnDate() != null) {
            LocalDate start = kunde.getDeliveryDate();
            LocalDate end = kunde.getReturnDate();
            long months = ChronoUnit.MONTHS.between(start, end);
            if (months == 0) months = 1;

            // håndtere null værdier fordi Double ikke er en primitiv datatype
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
