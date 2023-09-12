package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.ProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;


public class DtoMapperProduct {
    
    private Produit produit;

    private DtoMapperProduct(){
    }

    public static DtoMapperProduct builder(){
        return new DtoMapperProduct();
    }

    /* CONSTRUCTOR */
    public DtoMapperProduct setProduit(Produit produit ){
        this.produit = produit;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public ProduitDto build(){
        if(produit == null) {
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

        return new ProduitDto(
        this.produit.getId(), 
        this.produit.getCatProduit(), 
        this.produit.getUser(), 
        this.produit.getCode(), 
        this.produit.getNom(), 
        this.produit.getDescription(), 
        this.produit.getPrix(), 
        this.produit.getType_plat(), 
        this.produit.getCreatedOn(), 
        this.produit.getUpdatedOn(),
        this.produit.isStatut()
        );
    }


    
}
