package com.example.gruppe2_eksamen.model;

import jakarta.persistence.*;

@Entity
public class Rapport {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titel;

    @Column(length = 5000)
    private String beskrivelse;

    @ManyToOne
    @JoinColumn(name = "carId", nullable = true)
    private Car car;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
