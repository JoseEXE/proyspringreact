package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.AdresseDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;


public interface AdresseService {
    
    List<AdresseDto> findAll();

    Page<AdresseDto> findAll(Pageable pageable);

    Optional<AdresseDto> findById(Long id);

    AdresseDto save(Adresse adresse);

    Optional<AdresseDto> update(Adresse adresse, Long id);

    List<AdresseDto> findByClient(Client clientId);

    void remove(Long id);
}
