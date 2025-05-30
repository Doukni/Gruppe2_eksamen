package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.Rapport;
import com.example.gruppe2_eksamen.model.Skade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkadeRepo extends CrudRepository<Skade, Integer> {
    List<Skade> findByCar_Id(int id);
    List<Skade> findByCar(Car car);
}
