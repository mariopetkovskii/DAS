package com.example.userservice.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(String.format("User with username %s already exists", message));
    }
}
