package com.example.weatherservice.model.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException() {
        super(String.format("City not found!"));
    }
}
