package com.jose.backend.usersapp.backendusersapp.models.dto.mapper;

import com.jose.backend.usersapp.backendusersapp.models.dto.ProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;


public class DtoMapperProduct {
    
    private Produit produit;

    private DtoMapperProduct(){
        System.out.println("DtoMapperCatProduct 1");
    }

    public static DtoMapperProduct builder(){
        System.out.println("DtoMapperCatProduct 2 builder");
        return new DtoMapperProduct();
    }

    /* CONSTRUCTOR */
    public DtoMapperProduct setProduit(Produit produit){
        System.out.println("DtoMapperCatProduct 3 constructor");
        this.produit = produit;
        return this;
    }

    /* Si nous devons modifier la structure de ce que nous envoyons en réponse, 
    nous créons une nouvelle entité */

    public ProduitDto build(){
        System.out.println("DtoMapperCatProduct build");
        if(produit == null) {
            throw new RuntimeException("L'entité doit être transmise!!.");
        }

        System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§: "+produit.getCatProduit());
        System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§: "+produit.getUser());

        return new ProduitDto(
        this.produit.getId(), 
        this.produit.getCatProduit().getId(), 
        this.produit.getUser().getId(), 
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
