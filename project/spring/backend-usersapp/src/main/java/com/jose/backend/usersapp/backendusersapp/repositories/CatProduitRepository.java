package com.jose.backend.usersapp.backendusersapp.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jose.backend.usersapp.backendusersapp.models.entities.CatProduit;

public interface CatProduitRepository extends JpaRepository<CatProduit, Long>{
    
    Optional<CatProduit> findById(Long id);
    
    //Pagination
    Page<CatProduit> findAll(Pageable pageable);
    
}
