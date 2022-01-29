package com.example.userservice.service.impl;

import com.example.userservice.model.User;
import com.example.userservice.model.exceptions.InvalidArgumentException;
import com.example.userservice.model.exceptions.InvalidUserCredentialException;
import com.example.userservice.repository.jpa.UserRepository;
import com.example.userservice.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException("Invalid arguments!");
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialException::new);
    }

}