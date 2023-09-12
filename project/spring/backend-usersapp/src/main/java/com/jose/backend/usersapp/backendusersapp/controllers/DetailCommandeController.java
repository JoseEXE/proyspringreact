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

import com.jose.backend.usersapp.backendusersapp.models.dto.DetailCommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.DetailCommande;

import com.jose.backend.usersapp.backendusersapp.services.implementation.DetailCommandeServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/detailcommandes")
@CrossOrigin(originPatterns = "*")
public class DetailCommandeController {

    @Autowired
    private DetailCommandeServiceImp detailCommandeServiceImp;

    
    /*==============================================================*/
    /*1- CREATE DetailCommande */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DetailCommande detailcommande, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(detailCommandeServiceImp.save(detailcommande));
    }

    /*1- END CREATE DetailCommande */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ DetailCommande */

    @GetMapping
    public List<DetailCommandeDto> list(){
        return detailCommandeServiceImp.findAll();
    }

    /*2.1- END READ DetailCommande */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ DetailCommande pageable */
    @GetMapping("/page/{page}")
    public Page<DetailCommandeDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return detailCommandeServiceImp.findAll(pageable);
    } 
    /*2.2- END READ DetailCommande pageable */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE DetailCommande */

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody DetailCommande detailcommande, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<DetailCommandeDto> o = detailCommandeServiceImp.update(detailcommande, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE DetailCommande */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE DetailCommande */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<DetailCommandeDto> o = detailCommandeServiceImp.findById(id);
        
        if(o.isPresent()){
            detailCommandeServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }
    /*4- END DELETE  DetailCommande */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID DetailCommande */

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<DetailCommandeDto> detailCommandeOptional = detailCommandeServiceImp.findById(id);

	if(detailCommandeOptional.isPresent()){
		return ResponseEntity.ok(detailCommandeOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID DetailCommande */
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
