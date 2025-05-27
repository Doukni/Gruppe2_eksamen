package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.TilbageLevering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TilbageleveringRepo extends JpaRepository<TilbageLevering, Integer> {
    TilbageLevering findById(int id);
    List<TilbageLevering> findByCar(Car car);
}