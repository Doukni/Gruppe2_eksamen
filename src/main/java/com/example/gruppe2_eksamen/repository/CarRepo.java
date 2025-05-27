package com.example.gruppe2_eksamen.repository;


import com.example.gruppe2_eksamen.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CarRepo extends JpaRepository<Car, Integer> {
    List<Car> findByAvailability(String availability);
    List<Car> findByIsReturnedTrue();
}
