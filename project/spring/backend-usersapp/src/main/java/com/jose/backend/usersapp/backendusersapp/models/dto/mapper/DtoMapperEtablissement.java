package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;



import com.jose.backend.usersapp.backendusersapp.models.dto.EtablissementDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;


public class DtoMapperEtablissement {

    private Etablissement etablissement;
    
    private DtoMapperEtablissement(){
    }

    public static DtoMapperEtablissement builder(){
        return new DtoMapperEtablissement();
    } 

    /* CONSTRUCTOR */
    public DtoMapperEtablissement setEtablissement(Etablissement etablissement){
         this.etablissement = etablissement;
         return this;
    }
    
    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public EtablissementDto build(){
        if(etablissement == null) {
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

        return new EtablissementDto(    
            this.etablissement.getId(),
            this.etablissement.getNom(),
            this.etablissement.getSiret(),
            this.etablissement.getRue(),
            this.etablissement.getCod_postal(),
            this.etablissement.getVille(),
            this.etablissement.getNum_tel(),
            this.etablissement.getCreatedOn(),
            this.etablissement.getUpdatedOn(),
            this.etablissement.isStatut());
    }

}
