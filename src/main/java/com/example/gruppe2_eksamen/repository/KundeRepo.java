package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.Kunde;
import org.springframework.data.repository.CrudRepository;

public interface KundeRepo extends CrudRepository<Kunde, Integer> {
    Kunde findById(int id);
}
