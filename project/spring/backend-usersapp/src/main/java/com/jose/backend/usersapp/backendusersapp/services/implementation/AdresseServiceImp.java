package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.AdresseDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperAdresse;
import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import com.jose.backend.usersapp.backendusersapp.repositories.AdresseRepository;
import com.jose.backend.usersapp.backendusersapp.services.AdresseService;

@Service
public class AdresseServiceImp implements AdresseService{

    @Autowired
    private AdresseRepository adresseRepository;
     /*=======================================================*/
    /*1- CREATE Adresse  */
    @Override
    @Transactional
    public AdresseDto save(Adresse adresse) {
        System.out.println("Llega adresse : "+adresse);
        System.out.println("Llega adresse adresse.getClient().getId() : "+adresse.getClient().getId());
        System.out.println("Llega adresse adresse.getRue() : "+adresse.getRue());
        System.out.println("Llega adresse getCod_postal() : "+adresse.getCod_postal());
        System.out.println("Llega adresse getVille() : "+adresse.getVille());
        System.out.println("Llega adresse getComplement() : "+adresse.getComplement());
        System.out.println("Llega adresse getId(): "+adresse.getId());
        
        return DtoMapperAdresse.builder().setAdresse(adresseRepository.save(adresse)).build();
    }
     /*1- END CREATE Adresse  */
    /*=======================================================*/

    /*=======================================================*/
    /* 2.1- READ Adresse  */
    @Override
    @Transactional(readOnly = true)
    public List<AdresseDto> findAll() {
        List<Adresse> adresses = (List<Adresse>) adresseRepository.findAll();
        return adresses
                    .stream()
                    .map(u -> DtoMapperAdresse.builder().setAdresse(u).build())
                    .collect(Collectors.toList());  
    }
    /* 2.1- END READ Adresse  */
    @Override
    public List<AdresseDto> findByClient(Client clientId) {
    List<Adresse>  adresses =  adresseRepository.findByClient(clientId);
        System.out.println("adresses N0: "+adresses.size());
        return adresses
                    .stream()
                    .map(u -> DtoMapperAdresse.builder().setAdresse(u).build())
                    .collect(Collectors.toList());  
    }

    /*=======================================================*/
    /* 2.2- READ Adresse pageable */
    @Override
    @Transactional(readOnly = true)
    public Page<AdresseDto> findAll(Pageable pageable) {
        Page<Adresse> adressesPage = adresseRepository.findAll(pageable);
        return adressesPage.map(u -> DtoMapperAdresse.builder().setAdresse(u).build());
    }
    /* 2.2- END READ Adresse pageable */
    /*=======================================================*/
    /*=======================================================*/
    /*3- UPDATE Adresse  */
 
    @Override
    @Transactional
    public Optional<AdresseDto> update(Adresse adresse, Long id) {
        Optional<Adresse> o = adresseRepository.findById(id);
        Adresse adresseOptional = null;
        if(o.isPresent()){

            Adresse adresseDb = o.orElseThrow();
            
            adresseDb.setClient(adresse.getClient());
            adresseDb.setRue(adresse.getRue());
            adresseDb.setCod_postal(adresse.getCod_postal());
            adresseDb.setVille(adresse.getVille());
            adresseDb.setComplement(adresse.getComplement());
            adresseDb.setStatut(adresse.isStatut());
           
            adresseOptional = adresseRepository.save(adresseDb);
        }
        return Optional.ofNullable(DtoMapperAdresse.builder().setAdresse(adresseOptional).build());
    }
    /*3- END UPDATE Adresse  */
    /*=======================================================*/
    /*=======================================================*/
    /*4- DELETE Adresse  */
        @Override
        @Transactional
        public void remove(Long id) {
            adresseRepository.deleteById(id);
    }

    /*4- END DELETE Adresse  */
    /*=======================================================*/


    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/
    
    /*5- READ BY ID Adresse  */
        @Override
        @Transactional(readOnly = true)
        public Optional<AdresseDto> findById(Long id) {
            return adresseRepository.findById(id).map(u -> DtoMapperAdresse.builder().setAdresse(u).build());
    }
    /*5- END READ BY ID Adresse  */

   










    
}
