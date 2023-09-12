package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role>  findByName(String name);
}
