package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.DetailCommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.DetailCommande;

public class DtoMapperDetailCommande {
    private DetailCommande detailCommande;

    private DtoMapperDetailCommande(){

    }

    public static DtoMapperDetailCommande builder(){
        return new DtoMapperDetailCommande();
    }

    /* CONSTRUCTOR */
    public  DtoMapperDetailCommande setDetailCommande(DetailCommande detailCommande){
        this.detailCommande = detailCommande;
        return this;
    }

        /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public DetailCommandeDto build(){
        if(detailCommande == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }
        return new DetailCommandeDto(
            this.detailCommande.getId(),
            this.detailCommande.getCommande(),
            this.detailCommande.getProduit(),
            this.detailCommande.getQuantite(),
            this.detailCommande.getPrix_unitaire()
        );

    }
}
