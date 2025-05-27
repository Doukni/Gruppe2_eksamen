package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.ForhaandsAftale;
import com.example.gruppe2_eksamen.model.Rapport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForhaandsaftaleRepo extends CrudRepository< ForhaandsAftale ,Integer> {
    List<ForhaandsAftale> findByCar(Car car);
}
