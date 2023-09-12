package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.CatProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.CatProduit;


public interface CatProduitService {

    List<CatProduitDto> findAll();

    Page<CatProduitDto> findAll(Pageable pageable);

    Optional<CatProduitDto> findById(Long id);

    CatProduitDto save(CatProduit catProduit);

    Optional<CatProduitDto> update(CatProduit catProduit, Long id);

    void remove(Long id);

}
