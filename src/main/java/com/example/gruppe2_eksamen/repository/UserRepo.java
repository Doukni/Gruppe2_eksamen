package com.example.gruppe2_eksamen.repository;

import com.example.gruppe2_eksamen.model.User;
import org.springframework.data.repository.CrudRepository;


// Repo gør at man kan gemme, update og slette data fra User table
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByWorkId(String workId);
}
