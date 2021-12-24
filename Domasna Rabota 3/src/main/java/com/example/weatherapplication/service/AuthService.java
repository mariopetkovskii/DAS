package com.example.weatherapplication.service;

import com.example.weatherapplication.model.User;

public interface AuthService {
    User login(String username, String password);

    Object register(String username, String password, String repeatPassword, String name, String surname);


}
