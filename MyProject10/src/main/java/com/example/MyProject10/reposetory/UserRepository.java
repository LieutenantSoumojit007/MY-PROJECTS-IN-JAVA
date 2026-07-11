package com.example.MyProject10.reposetory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyProject10.model.User;

public interface UserRepository
        extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}