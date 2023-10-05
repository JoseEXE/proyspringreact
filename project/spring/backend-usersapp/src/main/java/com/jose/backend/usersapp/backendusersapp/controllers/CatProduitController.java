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

import com.jose.backend.usersapp.backendusersapp.models.dto.CatProduitDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.CatProduit;
import com.jose.backend.usersapp.backendusersapp.services.implementation.CatProductServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/catproduits")
@CrossOrigin(originPatterns = "*")
public class CatProduitController {

    @Autowired
    private CatProductServiceImp catProductServiceImp;   
    /*==============================================================*/
    /*1- CREATE Cat Produit paginable */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CatProduit catProduit, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(catProductServiceImp.save(catProduit));
    }
    /*1- END CREATE Cat Produit paginable */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ Cat Produit paginable */
    @GetMapping("/page/{page}")
    public Page<CatProduitDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return catProductServiceImp.findAll(pageable);
    } 
    /*2.1- END READ Cat Produit paginable */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ Cat Produit */
    @GetMapping
    public List<CatProduitDto> list(){
        return catProductServiceImp.findAll();
    }
    /*2.2- END READ Cat Produit */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE Cat Produit paginable */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CatProduit catProduit, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<CatProduitDto> o = catProductServiceImp.update(catProduit, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    /*3- END UPDATE Cat Produit paginable */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE Cat Produit paginable */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<CatProduitDto> o = catProductServiceImp.findById(id);
        
        if(o.isPresent()){
            catProductServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }
    /*4- END DELETE Cat Produit paginable */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID Cat Produit paginable */
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<CatProduitDto> catProductOptional = catProductServiceImp.findById(id);

	if(catProductOptional.isPresent()){
		return ResponseEntity.ok(catProductOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID Cat Produit paginable */
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
