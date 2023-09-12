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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.jose.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.UserDtoPass;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;
import com.jose.backend.usersapp.backendusersapp.models.request.UserRequestModForm;
import com.jose.backend.usersapp.backendusersapp.models.request.UserRequestModPass;
import com.jose.backend.usersapp.backendusersapp.repositories.UserRepository;
import com.jose.backend.usersapp.backendusersapp.services.implementation.UserServiceImp;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UseController {

    @Autowired
    private UserServiceImp userServiceImp;

    /* Mod */
    @Autowired
    private UserRepository userRepository;


    

    /* R */
    @GetMapping
    public List<UserDto> list(){
        return userServiceImp.findAll();
    }

    /* R */
    @GetMapping("/page/{page}")
    public Page<UserDto> list(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);

        return userServiceImp.findAll(pageable);
    }

    /* R Pass*/
    @GetMapping("/pass/{id}")
    public Optional<UserDto> getMyUser(@PathVariable Long id){
       
        System.out.println("=====================================entre");
        return userServiceImp.findById(id);
    }

    /* R By ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
	Optional<UserDto> userOptional = userServiceImp.findById(id);

	if(userOptional.isPresent()){
		return ResponseEntity.ok(userOptional.orElseThrow());
	}
	return ResponseEntity.notFound().build();
    }

    /* C */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImp.save(user));
    }

    /* U */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserRequestModForm user, 
    BindingResult result, @PathVariable Long id) {
   
    if(result.hasErrors()){
       return validation(result);
    }    
    Optional<UserDto> o = userServiceImp.update(user, id);
	if(o.isPresent()){
		return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
	}
	return ResponseEntity.notFound().build();

    }

    /* U PASS */
    @PutMapping("/pass/{id}")
    public ResponseEntity<?> updatePass(@Valid @RequestBody UserRequestModPass user, 
    BindingResult result, @PathVariable Long id) {
          
  
    if(result.hasErrors()){
       return validation(result);
    }    

    Optional<User> o = userRepository.findById(id);
    

    

	if(o.isPresent()){
         User userDb = o.orElseThrow();
        
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matched = bCryptPasswordEncoder.matches(user.getOldPassword(), userDb.getPassword());

            if(matched == true){
  
                userDb.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));


                Optional<UserDtoPass> ob = userServiceImp.updatepass(userDb, id);
                return ResponseEntity.status(HttpStatus.CREATED).body(ob.orElseThrow());
  
            }else{

                return new ResponseEntity<String>("Le mot de passe est incorrect!!...",HttpStatus.BAD_REQUEST);
            }
    

		//return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
	}
	return ResponseEntity.notFound().build();

    }


    /* D */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<UserDto> o = userServiceImp.findById(id);
        
        if(o.isPresent()){
            userServiceImp.remove(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); //404
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }



}





	

    

