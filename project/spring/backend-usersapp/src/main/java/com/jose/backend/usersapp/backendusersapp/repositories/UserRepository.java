package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{
    //List<User> findByEmail(String email);
    Optional<User>  findByEmail(String email);
    
    //Pagination #01
    Page<User> findAll(Pageable pageable);
}
