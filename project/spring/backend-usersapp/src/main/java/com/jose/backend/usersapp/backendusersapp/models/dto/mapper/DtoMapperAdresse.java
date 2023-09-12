package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.AdresseDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;

public class DtoMapperAdresse {
    private Adresse adresse;

    private DtoMapperAdresse(){
        
    }
    

    public static DtoMapperAdresse builder(){
        return new DtoMapperAdresse();
    }

    /* CONSTRUCTOR */
    public DtoMapperAdresse setAdresse(Adresse adresse){
        this.adresse = adresse;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public AdresseDto build(){
        if(adresse == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

        return new AdresseDto(
            this.adresse.getId(),
            this.adresse.getClient(),
            this.adresse.getRue(),
            this.adresse.getCod_postal(),
            this.adresse.getVille(),
            this.adresse.getComplement(),
            this.adresse.getCreatedOn(),
            this.adresse.getUpdatedOn(),
            this.adresse.isStatut());
            
    }





}
