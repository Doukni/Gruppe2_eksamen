package com.example.gruppe2_eksamen.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String color;

    @Column
    private String licensePlate;

    @Column (length=1000)
    private String carImageUrl;

    @Column // skadet, god osv
    private String carCondition;

    @Column //udlejet eller ej
    private String availability;

    @Column
    private Double price;

    @OneToOne(mappedBy = "car")
    private Kunde kunde;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String condition) {
        this.carCondition = condition;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}


