package com.example.gruppe2_eksamen.repository;



import com.example.gruppe2_eksamen.model.Car;
import com.example.gruppe2_eksamen.model.Rapport;
import com.example.gruppe2_eksamen.model.TilbageLevering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RapportRepo extends CrudRepository<Rapport, Long> {
    List<Rapport> findByCar(Car car);

}
