package com.jose.backend.usersapp.backendusersapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.backend.usersapp.backendusersapp.models.dto.ProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Produit;
import com.jose.backend.usersapp.backendusersapp.services.implementation.ProduitServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produits")
@CrossOrigin(originPatterns = "*")
public class ProduitController {

    @Autowired
    private ProduitServiceImp produitServiceImp;
      
    /*==============================================================*/
    /*1- CREATE Produit paginable */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Produit produit, BindingResult result){
        System.out.println("================================================="+produit);
        System.out.println("================================================="+produit.getCatProduit().getId());
        System.out.println("================================================="+produit.getUser().getId());
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(produitServiceImp.save(produit));

    }

    /*1- END CREATE Produit paginable */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ Cat Produit */
    @GetMapping
    public List<ProduitDto> list(){
        return produitServiceImp.findAll();
    }
    /*2.1- END READ Produit */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ Produit paginable */
    
    @GetMapping("/page/{page}")
    public Page<ProduitDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return produitServiceImp.findAll(pageable);
    } 
    /*2.2- END READ Produit paginable */
    
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE Produit paginable */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Produit produit, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<ProduitDto> o = produitServiceImp.update(produit, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE Produit paginable */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE Produit paginable */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ProduitDto> o = produitServiceImp.findById(id);
        
        if(o.isPresent()){
            produitServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }

    /*4- END DELETE  Produit paginable */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID Produit paginable */
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<ProduitDto> productOptional = produitServiceImp.findById(id);

	if(productOptional.isPresent()){
		return ResponseEntity.ok(productOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID Produit paginable */
    /*==============================================================*/

    /* VALIDATION FUNCTION */
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }




}
