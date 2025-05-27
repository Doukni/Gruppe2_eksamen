package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.TilbageLevering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TilbageleveringRepo extends JpaRepository<TilbageLevering, Integer> {
}