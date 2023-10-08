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

import com.jose.backend.usersapp.backendusersapp.models.dto.AdresseDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.ClientDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Adresse;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import com.jose.backend.usersapp.backendusersapp.services.implementation.AdresseServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adresses")
@CrossOrigin(originPatterns = "*")
public class AdresseController {

    @Autowired
    private AdresseServiceImp adresseServiceImp;

       
    /*==============================================================*/
    /*1- CREATE AdresseClient */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Adresse adresse, BindingResult result){

 

        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(adresseServiceImp.save(adresse));
    }

    /*1- END CREATE AdresseClient */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ AdresseClient */
    @GetMapping
    public List<AdresseDto> list(){
        return adresseServiceImp.findAll();
    }

    @PostMapping("/list")
    public List<AdresseDto> findByClient(@RequestBody Client clientId) {
        System.out.println("clientId.getId : "+clientId.getId());
        List<AdresseDto> o = adresseServiceImp.findByClient(clientId);

        return o;
    }


    /*2.1- END READ AdresseClient */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ AdresseClient pageable */
    @GetMapping("/page/{page}")
    public Page<AdresseDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return adresseServiceImp.findAll(pageable);
    } 
    /*2.2- END READ AdresseClient pageable */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE AdresseClient */

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Adresse adresse, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<AdresseDto> o = adresseServiceImp.update(adresse, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE AdresseClient */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE AdresseClient */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<AdresseDto> o = adresseServiceImp.findById(id);
        
        if(o.isPresent()){
            adresseServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }   
    /*4- END DELETE  AdresseClient */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID AdresseClient */

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<AdresseDto> adresseOptional = adresseServiceImp.findById(id);

	if(adresseOptional.isPresent()){
		return ResponseEntity.ok(adresseOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID AdresseClient */
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
