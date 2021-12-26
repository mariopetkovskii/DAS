package com.example.weatherapplication.service.impl;

import com.example.weatherapplication.model.User;
import com.example.weatherapplication.model.exceptions.InvalidArgumentException;
import com.example.weatherapplication.model.exceptions.InvalidUserCredentialException;
import com.example.weatherapplication.model.exceptions.PasswordDoNotMatchException;
import com.example.weatherapplication.model.exceptions.UsernameAlreadyExistsException;
import com.example.weatherapplication.repository.jpa.UserRepository;
import com.example.weatherapplication.service.AuthService;
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

//    @Override
//    public User register(String username, String password, String repeatPassword, String name, String surname) {
//        if(username == null || username.isEmpty() || password == null || password.isEmpty())
//            throw new InvalidArgumentException("Invalid arguments!");
//        if(!password.equals(repeatPassword)) {
//            throw new PasswordDoNotMatchException("Password do not match.");
//        }
//        if(this.userRepository.findByUsername(username).isPresent()
//                || !this.userRepository.findByUsername(username).isEmpty())
//            throw new UsernameAlreadyExistsException(username);
//        User user = new User(username, password, name, surname);
//        return userRepository.save(user);
//    }
}
