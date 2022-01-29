package com.example.userservice.service.impl;
import com.example.userservice.model.User;
import com.example.userservice.model.exceptions.InvalidArgumentException;
import com.example.userservice.model.exceptions.PasswordDoNotMatchException;
import com.example.userservice.model.exceptions.UsernameAlreadyExistsException;
import com.example.userservice.repository.jpa.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException("Invalid arguments!");
        if(!password.equals(repeatPassword)) {
            throw new PasswordDoNotMatchException("Password do not match!");
        }
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
