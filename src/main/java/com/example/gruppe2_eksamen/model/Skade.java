package com.example.gruppe2_eksamen.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Skade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String typeSkade;
    private String skadeBeskrivelse;

    private String billedeUrl;

    private LocalDate registrereDato;

    @ManyToOne
    private Car car;

    @Column
    private Double pris;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeSkade() {
        return typeSkade;
    }

    public void setTypeSkade(String typeSkade) {
        this.typeSkade = typeSkade;
    }

    public String getSkadeBeskrivelse() {
        return skadeBeskrivelse;
    }

    public void setSkadeBeskrivelse(String skadeBeskrivelse) {
        this.skadeBeskrivelse = skadeBeskrivelse;
    }

    public String getBilledeUrl() {
        return billedeUrl;
    }

    public void setBilledeUrl(String billedeUrl) {
        this.billedeUrl = billedeUrl;
    }

    public LocalDate getRegistrereDato() {
        return registrereDato;
    }

    public void setRegistrereDato(LocalDate registrereDato) {
        this.registrereDato = registrereDato;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }
}


