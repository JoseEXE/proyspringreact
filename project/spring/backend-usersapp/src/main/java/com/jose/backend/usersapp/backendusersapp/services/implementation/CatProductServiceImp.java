package com.jose.backend.usersapp.backendusersapp.services.implementation;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.CatProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperCatProduct;
import com.jose.backend.usersapp.backendusersapp.models.entities.CatProduit;
import com.jose.backend.usersapp.backendusersapp.repositories.CatProduitRepository;
import com.jose.backend.usersapp.backendusersapp.services.CatProduitService;


@Service
public class CatProductServiceImp implements CatProduitService{

    @Autowired
    private CatProduitRepository catProduitRepository;

    
    @Override /*1- CREATE Cat Produit paginable */
    @Transactional
    public CatProduitDto save(CatProduit catProduit) {
        return  DtoMapperCatProduct.builder().setCatProduit(catProduitRepository.save(catProduit)).build();
    } /*1- CREATE Cat Produit paginable */
    
    /*=======================================================*/
    
    @Override /*2.1- READ Cat Produit paginable */
    @Transactional(readOnly = true)
    public Page<CatProduitDto> findAll(Pageable pageable) {

        System.out.println("ENTRA A READ: "+pageable.getPageSize());
        Page<CatProduit> catProduitPage = catProduitRepository.findAll(pageable);
        return catProduitPage.map(u -> DtoMapperCatProduct.builder().setCatProduit(u).build());
    }/*2.1- END READ Cat Produit paginable */

    /*=======================================================*/

    @Override /*2.2- READ Cat Produit List */
    @Transactional(readOnly = true)
    public List<CatProduitDto> findAll() {
        System.out.println("Antes de Listar: =====================" );

        List<CatProduit> catProduit = (List<CatProduit>) catProduitRepository.findAll();

        return catProduit
                    .stream()
                    .map(u -> DtoMapperCatProduct.builder().setCatProduit(u).build())
                    .collect(Collectors.toList());  
    } /*2.2 READ Cat Produit List */

    /*=======================================================*/
    
    @Override /*3- UPDATE Cat Produit paginable */
    @Transactional
    public Optional<CatProduitDto> update(CatProduit catProduit, Long id) {
        Optional<CatProduit> o = catProduitRepository.findById(id);
        CatProduit catProduitOptional = null;
        if(o.isPresent()){

            CatProduit catProduitDb = o.orElseThrow();
            catProduitDb.setNom(catProduit.getNom());
            catProduitDb.setDescription(catProduit.getDescription());

            catProduitOptional = catProduitRepository.save(catProduitDb);
        }
        return Optional.ofNullable(DtoMapperCatProduct.builder().setCatProduit(catProduitOptional).build());

    } /*3- END UPDATE Cat Produit paginable */

    /*=======================================================*/
    
    @Override /*4- END DELETE Cat Produit paginable */
    @Transactional
    public void remove(Long id) {
        catProduitRepository.deleteById(id);
    } /*4- END DELETE Cat Produit paginable */
    
    
    /*=======================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||*/
    
    
    @Override /*5- READ BY ID Cat Produit  */
    @Transactional(readOnly = true)
    public Optional<CatProduitDto> findById(Long id) {
        return catProduitRepository.findById(id).map(u -> DtoMapperCatProduct.builder().setCatProduit(u).build());
    } /*5- END READ BY ID Cat Produit  */

   
}
