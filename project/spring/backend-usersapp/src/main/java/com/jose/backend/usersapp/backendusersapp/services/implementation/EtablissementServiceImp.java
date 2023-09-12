package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.EtablissementDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperEtablissement;
import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;
import com.jose.backend.usersapp.backendusersapp.repositories.EtablissementRepository;
import com.jose.backend.usersapp.backendusersapp.services.EtablissementService;


@Service
public class EtablissementServiceImp implements EtablissementService {

    @Autowired
    private EtablissementRepository etablissementRepository;

    /*=======================================================*/
    /*1- CREATE Etablissement  */
    @Override
    @Transactional
    public EtablissementDto save(Etablissement etablissement) {

        return DtoMapperEtablissement.builder().setEtablissement(etablissementRepository.save(etablissement)).build();
    }
     /*1- END CREATE Etablissement  */
    /*=======================================================*/

    /*=======================================================*/
    /* 2.1- READ Etablissement  */
    @Override
    @Transactional(readOnly = true)
    public List<EtablissementDto> findAll() {
        List<Etablissement> etablissement = (List<Etablissement>) etablissementRepository.findAll();

        return etablissement
                    .stream()
                    .map(u -> DtoMapperEtablissement.builder().setEtablissement(u).build())
                    .collect(Collectors.toList());  
    }
    /* 2.1- END READ Etablissement  */
    /*=======================================================*/
    /* 2.2- READ Etablissement pageable */
    @Override
    @Transactional(readOnly = true)
    public Page<EtablissementDto> findAll(Pageable pageable) {
        Page<Etablissement> etablissement = etablissementRepository.findAll(pageable);
        return etablissement.map(u -> DtoMapperEtablissement.builder().setEtablissement(u).build());
    }
    /* 2.2- END READ Etablissement pageable */
    /*=======================================================*/
    /*=======================================================*/
    /*3- UPDATE Etablissement  */
    @Override
    @Transactional
    public Optional<EtablissementDto> update(Etablissement etablissement, Long id) {
        Optional<Etablissement> o = etablissementRepository.findById(id);
        Etablissement etablissementOptional = null;
        if(o.isPresent()){

            Etablissement etablissementDb = o.orElseThrow();

            etablissementDb.setNom(etablissement.getNom());
            etablissementDb.setSiret(etablissement.getSiret());
            etablissementDb.setRue(etablissement.getRue());
            etablissementDb.setCod_postal(etablissement.getCod_postal());
            etablissementDb.setVille(etablissement.getVille());
            etablissementDb.setNum_tel(etablissement.getNum_tel());
            etablissementDb.setCreatedOn(etablissement.getCreatedOn());
            etablissementDb.setUpdatedOn(etablissement.getUpdatedOn());
            etablissementDb.setStatut(etablissement.isStatut());
           
            etablissementOptional = etablissementRepository.save(etablissementDb);
        }
        return Optional.ofNullable(DtoMapperEtablissement.builder().setEtablissement(etablissementOptional).build());
    }
    /*3- END UPDATE Etablissement  */
    /*=======================================================*/
    /*=======================================================*/
    /*4- DELETE Etablissement  */
    @Override
    @Transactional
    public void remove(Long id) {
        etablissementRepository.deleteById(id);
    }
    /*4- END DELETE Etablissement  */
    /*=======================================================*/


    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/
   
    /*5- READ BY ID Etablissement  */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtablissementDto> findById(Long id) {
        return etablissementRepository.findById(id).map(u -> DtoMapperEtablissement.builder().setEtablissement(u).build());
    }
    /*5- ENDREAD BY ID Etablissement  */








    
}
