package com.jose.backend.usersapp.backendusersapp.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.UserDtoPass;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperUser;
import com.jose.backend.usersapp.backendusersapp.models.dto.mapper.DtoMapperUserPass;
import com.jose.backend.usersapp.backendusersapp.models.entities.Role;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;
import com.jose.backend.usersapp.backendusersapp.models.interfaces.IUser;
import com.jose.backend.usersapp.backendusersapp.models.request.UserRequestModForm;
import com.jose.backend.usersapp.backendusersapp.repositories.RoleRepository;
import com.jose.backend.usersapp.backendusersapp.repositories.UserRepository;
import com.jose.backend.usersapp.backendusersapp.services.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    



    // R Only
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users
                    .stream()
                    .map(u -> DtoMapperUser.builder().setUser(u).build())
                    .collect(Collectors.toList());     
    }
    // END R Only

    // R by ID
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        /* OPTION 1 */
    /*  Optional<User> o = userRepository.findById(id);
        if(o.isPresent()){
            return Optional.of(DtoMapperUser.builder().setUser(o.orElseThrow()).build());
        }
		return Optional.empty();
    */
        /* OPTION 2 */
        return userRepository.findById(id).map(u -> DtoMapperUser.builder().setUser(u).build());
    } 
    // END R by ID

    // C
    @Override
    @Transactional
    public UserDto save(User user) {
        
        String bCryptPasswordEncoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPasswordEncoder);

        //List<Role> roles = getRoles(user);

        user.setRoles(getRoles(user));
		return  DtoMapperUser.builder().setUser(userRepository.save(user)).build();
    }
    // END C

    // U
    @Override
    @Transactional
    public Optional<UserDto> update(UserRequestModForm user, Long id) {

        Optional<User> o = userRepository.findById(id);
        User userOptional = null;
        if(o.isPresent()){

            //List<Role> roles = getRoles(user);

            User userDb = o.orElseThrow();
            userDb.setRoles(getRoles(user));
            userDb.setNameU(user.getNameU());
            userDb.setLastName(user.getLastName());
            userDb.setEmail(user.getEmail());
            userOptional = userRepository.save(userDb);
        }
            
        return Optional.ofNullable(DtoMapperUser.builder().setUser(userOptional).build());

    }
    // END U

    // D
    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
    // END D

    

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(u -> DtoMapperUser.builder().setUser(u).build());
    }

    @Override
    @Transactional
    public Optional<UserDtoPass> updatepass(User user, Long id) {

        User userOptional = null;
        userOptional = userRepository.save(user);
            
        return Optional.ofNullable(DtoMapperUserPass.builder().setUserPass(userOptional).build()); //.setUser(userOptional).build());

    }

    /* Obtention de Roles... */
    private List<Role> getRoles(IUser user){
        Optional<Role> ou = roleRepository.findByName("ROLE_USER");
            List<Role> roles = new ArrayList<>();
            if(ou.isPresent()){
                roles.add(ou.orElseThrow());
            }

            if(user.isAdmin()){
                Optional<Role> oa = roleRepository.findByName("ROLE_ADMIN");
                if(oa.isPresent()){
                    roles.add(oa.orElseThrow());
                }
            }
            return roles;
    }
    
}
