package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.backend.usersapp.backendusersapp.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.jose.backend.usersapp.backendusersapp.models.entities.User> o =userRepository.findByEmail(username);

        if(!o.isPresent()){

            throw new UsernameNotFoundException(String.format("Le user %s n'existe pas ! ", username));
        }
        com.jose.backend.usersapp.backendusersapp.models.entities.User user = o.orElseThrow();

        /*   Obtention des Roles   */
        //List<GrantedAuthority> authorities = new ArrayList<>();
        //    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));;
        List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map(r -> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());



        String userDetail = user.getEmail() +":"+ user.getLastName() + " " + user.getNameU() + ":" + user.getId(); 

        return new User(
        userDetail, 
        user.getPassword(), 
        true,
        true,
        true,
        true, 
        authorities ); 
    }
    
}
