package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.ProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperProduct;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;
import com.jose.backend.usersapp.backendusersapp.repositories.ProduitRepository;
import com.jose.backend.usersapp.backendusersapp.services.ProduitService;

@Service
public class ProduitServiceImp implements ProduitService{

    @Autowired
    private ProduitRepository produitRepository;

    
    @Override /*1- CREATE Produit paginable */
    @Transactional
    public ProduitDto save(Produit produit) {
        
        return  DtoMapperProduct.builder().setProduit(produitRepository.save(produit)).build();
    } /*1- END CREATE Produit paginable */
    
    /*=======================================================*/
    
    @Override /* 2.1- READ Produit paginable */
    @Transactional(readOnly = true)
    public Page<ProduitDto> findAll(Pageable pageable) {
        Page<Produit> produitPage = produitRepository.findAll(pageable);
        return produitPage.map(u -> DtoMapperProduct.builder().setProduit(u).build());
    } /* 2.1- END READ Produit paginable */

    /*=======================================================*/

    @Override /* 2.2- READ Produit paginable */
    public List<ProduitDto> findAll() {


    List<Produit> produit = (List<Produit>) produitRepository.findAll();

    return produit
                .stream()
                .map(u -> DtoMapperProduct.builder().setProduit(u).build())
                .collect(Collectors.toList());  

    } /* 2.2- READ Produit paginable */
    

    /*=======================================================*/
    
    @Override /*3- UPDATE Produit paginable */
    @Transactional
    public Optional<ProduitDto> update(Produit produit, Long id) {
        Optional<Produit> o = produitRepository.findById(id);
        Produit produitOptional = null;
        if(o.isPresent()){


            Produit produitDb = o.orElseThrow();
            produitDb.setCatProduit(produit.getCatProduit());
            produitDb.setUser(produit.getUser());
            produitDb.setCode(produit.getCode());
            produitDb.setNom(produit.getNom());
            produitDb.setDescription(produit.getDescription());
            produitDb.setPrix(produit.getPrix());
            produitDb.setType_plat(produit.getType_plat());

            
            produitOptional = produitRepository.save(produitDb);
        }
        return Optional.ofNullable(DtoMapperProduct.builder().setProduit(produitOptional).build());

    }
    
    
    /*=======================================================*/

    @Override /*4- DELETE Produit paginable */
    @Transactional
    public void remove(Long id) {
        produitRepository.deleteById(id);
    } /*4- END DELETE Produit paginable */



    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/

    @Override /*5- READ BY ID Produit paginable */
    @Transactional(readOnly = true)
    public Optional<ProduitDto> findById(Long id) {
        return produitRepository.findById(id).map(u -> DtoMapperProduct.builder().setProduit(u).build());
    } /*5- READ BY ID Produit paginable */





    
}
