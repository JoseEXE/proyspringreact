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

import com.jose.backend.usersapp.backendusersapp.models.dto.ClientDto;
import com.jose.backend.usersapp.backendusersapp.models.entities.Client;
import com.jose.backend.usersapp.backendusersapp.services.implementation.ClientServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
@CrossOrigin(originPatterns = "*")
public class ClientController {

    @Autowired
    private ClientServiceImp clientServiceImp;

    /*==============================================================*/
    /*1- CREATE Client */

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(clientServiceImp.save(client));

    }
    /*1- END CREATE Client */
    /*==============================================================*/   
    /*==============================================================*/
    /*2.1- READ Client */
    @GetMapping
    public List<ClientDto> list(){
        return clientServiceImp.findAll();
    }
    /*2.1- END READ Client */
    /*==============================================================*/
    /*==============================================================*/
    /*2.2- READ Client pageable */
    @GetMapping("/page/{page}")
    public Page<ClientDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return clientServiceImp.findAll(pageable);
    } 
    /*2.2- END READ Client pageable */
    /*==============================================================*/
    /*==============================================================*/
    /*3- UPDATE Client */

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client, 
    BindingResult result, @PathVariable Long id) {
   
        if(result.hasErrors()){
        return validation(result);
        }    
        Optional<ClientDto> o = clientServiceImp.update(client, id);
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /*3- END UPDATE Client */
    /*==============================================================*/
    /*==============================================================*/
    /*4- DELETE Client */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<ClientDto> o = clientServiceImp.findById(id);
        
        if(o.isPresent()){
            clientServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }
    /*4- END DELETE  Client */
    /*==============================================================*/

    

    /*==============================================================*/
    /*|||||||||||||||||||||OPTIONNEL||||||||||||||||||||||||||||||||||*/
    /*|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||---*/
    /*==============================================================*/
    /*5- READ BY ID Client */

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<ClientDto> clientOptional = clientServiceImp.findById(id);

	if(clientOptional.isPresent()){
		return ResponseEntity.ok(clientOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /*5- READ BY ID Client */
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
