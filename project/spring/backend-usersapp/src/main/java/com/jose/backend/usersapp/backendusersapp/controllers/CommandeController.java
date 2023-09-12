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

import com.jose.backend.usersapp.backendusersapp.models.dto.CommandeDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Commande;
import com.jose.backend.usersapp.backendusersapp.services.implementation.CommandeServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/commandes")
@CrossOrigin(originPatterns = "*")
public class CommandeController {
    
    @Autowired
    private CommandeServiceImp commandeServiceImp;

    /*==============================================================*/
    /*1- CREATE Commande */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Commande commande, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(commandeServiceImp.save(commande));

    }


    /*1- END CREATE Commande */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ Commande */
    @GetMapping
    public List<CommandeDto> list(){
        return commandeServiceImp.findAll();
    }
    /*2.1- END READ Commande */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ Commande pageable */
    @GetMapping("/page/{page}")
    public Page<CommandeDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return commandeServiceImp.findAll(pageable);
    } 
    /*2.2- END READ Commande pageable */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE Commande */

        @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Commande commande, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<CommandeDto> o = commandeServiceImp.update(commande, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE Commande */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE Commande */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<CommandeDto> o = commandeServiceImp.findById(id);
        
        if(o.isPresent()){
            commandeServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }   
    /*4- END DELETE  Commande */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID Commande */

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<CommandeDto> commandeOptional = commandeServiceImp.findById(id);

	if(commandeOptional.isPresent()){
		return ResponseEntity.ok(commandeOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID Commande */
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
