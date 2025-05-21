package com.example.gruppe2_eksamen.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private Integer age;

    @Column
    private Integer driverLicenseNumber;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private Boolean kreditgodkendt;

    @Column
    private Boolean betaltForsteYdelse;

    @Column // limeted(150 dage), unlimted(3 måneder)
    private String limitedOrUnlimited;

    @Column
    private LocalDate returnDate;

    @Column
    private LocalDate deliveryDate;

    @OneToOne
    @JoinColumn(name = "car_id") // connecter kunde til bil og viser hvilken han bruger
    private Car car;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDriverLicenseNumber(Integer driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getLimitedOrUnlimited() {
        return limitedOrUnlimited;
    }

    public void setLimitedOrUnlimited(String limitedOrUnlimited) {
        this.limitedOrUnlimited = limitedOrUnlimited;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(int driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getKreditgodkendt() {
        return kreditgodkendt;
    }

    public void setKreditgodkendt(Boolean kreditgodkendt) {
        this.kreditgodkendt = kreditgodkendt;
    }

    public Boolean getBetaltForsteYdelse() {
        return betaltForsteYdelse;
    }

    public void setBetaltForsteYdelse(Boolean betaltForsteYdelse) {
        this.betaltForsteYdelse = betaltForsteYdelse;
    }

}
