package com.example.userservice.service;

import com.example.userservice.model.User;

public interface AuthService {
    User login(String username, String password);



}