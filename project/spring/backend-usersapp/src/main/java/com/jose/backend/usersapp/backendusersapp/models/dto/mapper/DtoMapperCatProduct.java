package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.CatProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.CatProduit;

public class DtoMapperCatProduct {

    private CatProduit catProduit;

    private DtoMapperCatProduct(){

    }
    
    public static DtoMapperCatProduct builder(){
        return new DtoMapperCatProduct();
    }

    /* CONSTRUCTOR */
    public DtoMapperCatProduct setCatProduit(CatProduit catProduit){
        this.catProduit = catProduit;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public CatProduitDto build(){
        if(catProduit == null){
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

         return new CatProduitDto(this.catProduit.getId(), this.catProduit.getNom(), this.catProduit.getDescription(), this.catProduit.isStatut());
    }

}
