package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.ForhaandsAftale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForhaandsaftaleRepo extends CrudRepository< ForhaandsAftale ,Integer> {
}
