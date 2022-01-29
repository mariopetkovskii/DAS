package com.example.userservice.model.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException() {
        super(String.format("City not found!"));
    }
}
