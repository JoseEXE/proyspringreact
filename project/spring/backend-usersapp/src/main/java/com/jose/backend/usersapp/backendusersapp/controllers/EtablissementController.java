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

import com.jose.backend.usersapp.backendusersapp.models.dto.EtablissementDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Etablissement;
import com.jose.backend.usersapp.backendusersapp.services.implementation.EtablissementServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/etablissements")
@CrossOrigin(originPatterns = "*")
public class EtablissementController {
 
    @Autowired
    private EtablissementServiceImp etablissementServiceImp;

    
    /*==============================================================*/
    /*1- CREATE Etablissement */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Etablissement etablissement, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(etablissementServiceImp.save(etablissement));

    }

    /*1- END CREATE Etablissement */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ Etablissement */
    @GetMapping
    public List<EtablissementDto> list(){
        return etablissementServiceImp.findAll();
    }
    /*2.1- END READ Etablissement */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ Etablissement pageable */
    @GetMapping("/page/{page}")
    public Page<EtablissementDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return etablissementServiceImp.findAll(pageable);
    } 
    /*2.2- END READ Etablissement pageable */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE Etablissement */

        @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Etablissement etablissement, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<EtablissementDto> o = etablissementServiceImp.update(etablissement, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE Etablissement */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE Etablissement */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<EtablissementDto> o = etablissementServiceImp.findById(id);
        
        if(o.isPresent()){
            etablissementServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }
    /*4- END DELETE  Etablissement */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID Etablissement */

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<EtablissementDto> etablissementOptional = etablissementServiceImp.findById(id);

	if(etablissementOptional.isPresent()){
		return ResponseEntity.ok(etablissementOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID Etablissement */
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
