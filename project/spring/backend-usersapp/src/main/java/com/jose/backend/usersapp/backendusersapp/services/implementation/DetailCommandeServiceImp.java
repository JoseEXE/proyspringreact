package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.DetailCommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperDetailCommande;
import com.jose.backend.usersapp.backendusersapp.models.entities.DetailCommande;
import com.jose.backend.usersapp.backendusersapp.repositories.DetailCommandeRepository;
import com.jose.backend.usersapp.backendusersapp.services.DetailCommandeService;

@Service
public class DetailCommandeServiceImp implements DetailCommandeService{

    @Autowired
    private DetailCommandeRepository detailCommandeRepository;

    /*=======================================================*/
    /*1- CREATE DetailCommande  */

    @Override
    @Transactional
    public DetailCommandeDto save(DetailCommande detailCommande) {

        return DtoMapperDetailCommande.builder().setDetailCommande(detailCommandeRepository.save(detailCommande)).build();
    }
     /*1- END CREATE DetailCommande  */
    /*=======================================================*/

    /*=======================================================*/
    /* 2.1- READ DetailCommande  */
    @Override
    @Transactional(readOnly = true)
    public List<DetailCommandeDto> findAll() {
        List<DetailCommande> detailCommandes = (List<DetailCommande>) detailCommandeRepository.findAll();

        return detailCommandes
                    .stream()
                    .map(u -> DtoMapperDetailCommande.builder().setDetailCommande(u).build())
                    .collect(Collectors.toList());  
    }
    /* 2.1- END READ DetailCommande  */
    /*=======================================================*/
    /* 2.2- READ DetailCommande pageable */

    @Override
    @Transactional(readOnly = true)
    public Page<DetailCommandeDto> findAll(Pageable pageable) {
        Page<DetailCommande> detailCommandes = detailCommandeRepository.findAll(pageable);
        return detailCommandes.map(u -> DtoMapperDetailCommande.builder().setDetailCommande(u).build());
    }
    /* 2.2- END READ DetailCommande pageable */
    /*=======================================================*/
    /*=======================================================*/
    /*3- UPDATE DetailCommande  */
    @Override
    @Transactional
    public Optional<DetailCommandeDto> update(DetailCommande detailCommande, Long id) {
        Optional<DetailCommande> o = detailCommandeRepository.findById(id);
        DetailCommande detailCommandeOptional = null;
        if(o.isPresent()){

            DetailCommande detailCommandeDb = o.orElseThrow();

            detailCommandeDb.setCommande(detailCommande.getCommande());
            detailCommandeDb.setProduit(detailCommande.getProduit());
            detailCommandeDb.setQuantite(detailCommande.getQuantite());
            detailCommandeDb.setPrix_unitaire(detailCommande.getPrix_unitaire());

            detailCommandeOptional = detailCommandeRepository.save(detailCommandeDb);
        }
        return Optional.ofNullable(DtoMapperDetailCommande.builder().setDetailCommande(detailCommandeOptional).build());
    }
    /*3- END UPDATE DetailCommande  */
    /*=======================================================*/
    /*=======================================================*/
    /*4- DELETE DetailCommande  */
    @Override
    @Transactional
    public void remove(Long id) {
        detailCommandeRepository.deleteById(id);
    }
    /*4- END DELETE DetailCommande  */
    /*=======================================================*/

    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/

    /*5- READ BY ID DetailCommande  */
    @Override
    @Transactional(readOnly = true)
    public Optional<DetailCommandeDto> findById(Long id) {
        return detailCommandeRepository.findById(id).map(u -> DtoMapperDetailCommande.builder().setDetailCommande(u).build());
    }
    /*5- END READ BY ID DetailCommande  */










    
}
