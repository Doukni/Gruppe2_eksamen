package com.example.gruppe2_eksamen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ForhaandsAftale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;

    @Column
    private String buyerName;

    @Column
    private LocalDate agreementDate;

    @Column
    private int expectedKm;

    @Column
    private int actualKm;

    @Column
    private String currency;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String pickupLocation;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public LocalDate getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(LocalDate agreementDate) {
        this.agreementDate = agreementDate;
    }

    public int getExpectedKm() {
        return expectedKm;
    }

    public void setExpectedKm(int expectedKm) {
        this.expectedKm = expectedKm;
    }

    public int getActualKm() {
        return actualKm;
    }

    public void setActualKm(int actualKm) {
        this.actualKm = actualKm;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

}
