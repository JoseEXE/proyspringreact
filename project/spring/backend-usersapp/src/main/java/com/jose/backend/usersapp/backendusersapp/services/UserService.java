package com.jose.backend.usersapp.backendusersapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jose.backend.usersapp.backendusersapp.models.dto.UserDto;
import com.jose.backend.usersapp.backendusersapp.models.dto.UserDtoPass;
import com.jose.backend.usersapp.backendusersapp.models.entities.User;
import com.jose.backend.usersapp.backendusersapp.models.request.UserRequestModForm;


public interface UserService {
    List<UserDto> findAll();

    //Pagination #01
    Page<UserDto> findAll(Pageable pageable);

    Optional<UserDto> findById(Long id);

    UserDto save(User user);

    Optional<UserDto> update(UserRequestModForm user, Long id);

    Optional<UserDtoPass> updatepass(User user, Long id);

    void remove(Long id);
}
