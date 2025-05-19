package com.example.gruppe2_eksamen.repository;


import com.example.gruppe2_eksamen.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepo extends CrudRepository<Car, Integer> {
    Car findById(int id);
}
