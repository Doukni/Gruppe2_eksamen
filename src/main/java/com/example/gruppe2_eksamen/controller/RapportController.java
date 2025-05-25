package com.example.gruppe2_eksamen.controller;


import com.example.gruppe2_eksamen.model.Rapport;
import com.example.gruppe2_eksamen.model.Skade;
import com.example.gruppe2_eksamen.model.User;
import com.example.gruppe2_eksamen.repository.CarRepo;
import com.example.gruppe2_eksamen.repository.RapportRepo;
import com.example.gruppe2_eksamen.repository.SkadeRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RapportController {

    @Autowired
    private RapportRepo rapportRepo;

    @Autowired
    private SkadeRepo skadeRepo;
    @Autowired
    private CarRepo carRepo;

    @GetMapping("/rapport")
    public String rapportPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
            model.addAttribute("user", loggedUser);
        }

        model.addAttribute("rapporter", rapportRepo.findAll());
        model.addAttribute("cars", carRepo.findAll());
        model.addAttribute("rapport", new Rapport());
        return "rapport";
    }

    @PostMapping("/rapport")
    public String rapportPost(@ModelAttribute Rapport rapport, HttpSession session) {

        // Hent bilen ud fra det valgte ID
        if (rapport.getCar() != null && rapport.getCar().getId() != 0) {
            int carId = rapport.getCar().getId();

            // Find bilen i databasen og sæt den eksplicit
            carRepo.findById(carId).ifPresent(rapport::setCar);

            // Find skader for bilen
            List<Skade> skader = skadeRepo.findByCar_Id(carId);

            if (!skader.isEmpty()) {
                StringBuilder sb = new StringBuilder();

                // 1. Tilføj det brugeren selv har skrevet
                String brugerTekst = rapport.getBeskrivelse();
                if (brugerTekst != null && !brugerTekst.isBlank()) {
                    sb.append("Rapport:\n").append(brugerTekst).append("\n\n");
                }

                // 2. Tilføj skader
                sb.append("Skader:\n");
                double totalPris = 0.0;

                for (Skade skade : skader) {
                    sb.append("- ").append(skade.getSkadeBeskrivelse());

                    if (skade.getPris() != null) {
                        sb.append(" (").append(skade.getPris()).append(" kr)");
                        totalPris += skade.getPris();
                    } else {
                        sb.append(" (ingen pris angivet)");
                    }

                    sb.append("\n");
                }

                // 3. Samlet pris
                sb.append("\nSamlet pris: ").append(totalPris).append(" kr");

                // 4. Sæt samlet beskrivelse i rapporten
                rapport.setBeskrivelse(sb.toString());
            }
        }

        rapportRepo.save(rapport);
        return "redirect:/rapport";
    }

    @GetMapping("/rapporter")
    public String visAlleRapporter(HttpSession session, Model model) {
        model.addAttribute("rapporter", rapportRepo.findAll());
        return "rapport-oversigt";
    }
}
