package com.example.weatherapplication.repository.jpa;

import com.example.weatherapplication.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);
}
