package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.CommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;

public class DtoMapperCommande {
    
    private Commande commande;

    private DtoMapperCommande(){

    }

    public static DtoMapperCommande builder(){
        return new DtoMapperCommande();
    }

    /* CONSTRUCTOR */
    public DtoMapperCommande setCommande(Commande commande){
        this.commande = commande;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */


    public CommandeDto build(){
        if(commande == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }
        return new CommandeDto(
            this.commande.getId(),
            this.commande.getClient(),
            this.commande.getUser(),
            this.commande.getCommentaire(),
            this.commande.getTotalHl(),
            this.commande.getTotal(),
            this.commande.getType_paiement(),
            this.commande.getEtat(),
            this.commande.getCreatedOn()
        );
    }


}
