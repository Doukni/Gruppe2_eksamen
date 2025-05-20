package com.example.gruppe2_eksamen.repository;


import com.example.gruppe2_eksamen.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Integer> {
    List<Car> findByAvailability(String availability);
}
