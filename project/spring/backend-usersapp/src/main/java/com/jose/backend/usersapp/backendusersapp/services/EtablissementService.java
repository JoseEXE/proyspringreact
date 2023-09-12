package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.EtablissementDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;


public interface EtablissementService {
    
    List<EtablissementDto> findAll();

    Page<EtablissementDto> findAll(Pageable pageable);

    Optional<EtablissementDto> findById(Long id);

    EtablissementDto save(Etablissement etablissement);

    Optional<EtablissementDto> update(Etablissement etablissement, Long id);

    void remove(Long id);

}
