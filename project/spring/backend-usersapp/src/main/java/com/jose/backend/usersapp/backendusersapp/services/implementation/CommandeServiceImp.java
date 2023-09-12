package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.CommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperCommande;
import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;
import com.jose.backend.usersapp.backendusersapp.repositories.CommandeRepository;
import com.jose.backend.usersapp.backendusersapp.services.CommandeService;

@Service
public class CommandeServiceImp implements CommandeService{

    @Autowired
    private CommandeRepository commandeRepository;
    
    /*=======================================================*/
    /*1- CREATE Commande  */
    @Override
    @Transactional
    public CommandeDto save(Commande commande) {

        return DtoMapperCommande.builder().setCommande(commandeRepository.save(commande)).build();
    }
     /*1- END CREATE Commande  */
    /*=======================================================*/

    /*=======================================================*/
    /* 2.1- READ Commande  */
    @Override
    @Transactional(readOnly = true)
    public List<CommandeDto> findAll() {
        List<Commande> commandes = (List<Commande>) commandeRepository.findAll();

        return commandes
                    .stream()
                    .map(u -> DtoMapperCommande.builder().setCommande(u).build())
                    .collect(Collectors.toList());  
    }
    /* 2.1- END READ Commande  */
    /*=======================================================*/
    /* 2.2- READ Commande pageable */
    @Override
    @Transactional(readOnly = true)
    public Page<CommandeDto> findAll(Pageable pageable) {
        Page<Commande> commandes = commandeRepository.findAll(pageable);
        return commandes.map(u -> DtoMapperCommande.builder().setCommande(u).build());
    }
    /* 2.2- END READ Commande pageable */
    /*=======================================================*/
    /*=======================================================*/
    /*3- UPDATE Commande  */
    @Override
    @Transactional
    public Optional<CommandeDto> update(Commande commande, Long id) {

        Optional<Commande> o = commandeRepository.findById(id);
        Commande commandeOptional = null;
        if(o.isPresent()){

            Commande commandeDb = o.orElseThrow();

            commandeDb.setClient(commande.getClient());
            commandeDb.setUser(commande.getUser());
            commandeDb.setCommentaire(commande.getCommentaire());
            commandeDb.setTotalHl(commande.getTotalHl());
            commandeDb.setTotal(commande.getTotal());
            commandeDb.setType_paiement(commande.getType_paiement());
            commandeDb.setEtat(commande.getEtat());
          
            commandeOptional = commandeRepository.save(commande);
        }
        return Optional.ofNullable(DtoMapperCommande.builder().setCommande(commandeOptional).build());
    }
    /*3- END UPDATE Commande  */
    /*=======================================================*/
    /*=======================================================*/
    /*4- DELETE Commande  */
    @Override
    @Transactional
    public void remove(Long id) {
        commandeRepository.deleteById(id);
    }
    /*4- END DELETE Commande  */
    /*=======================================================*/

    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/
    
    /*5- READ BY ID Commande  */
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CommandeDto> findById(Long id) {
        return commandeRepository.findById(id).map(u -> DtoMapperCommande.builder().setCommande(u).build());
    }
    /*5- END READ BY ID Commande  */
}
