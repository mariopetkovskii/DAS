package com.example.weatherservice.controller;

import com.example.weatherservice.model.Foo;
import com.example.weatherservice.model.exceptions.CityNotFoundException;
import com.example.weatherservice.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home", "/gradovi"})
public class CityController {
    private final WeatherService weatherService;

    public CityController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    String getHomePage(@RequestParam(required = false) String error, Model model, HttpServletRequest req, RestTemplate restTemplate) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String search = (String) req.getSession().getAttribute("search");
        String resourceUrl;
        if(search != null){
            resourceUrl = "http://api.weatherapi.com/v1/current.json?key=49c446e04a81488591322208212209&q=" + search + "&aqi=yes";
        }else {
            resourceUrl = "http://api.weatherapi.com/v1/current.json?key=49c446e04a81488591322208212209&q=Skopje&aqi=yes";
        }
        Foo foo = restTemplate
                .getForObject(resourceUrl, Foo.class);
        model.addAttribute("items", foo);
        model.addAttribute("bodyContent", "gradovi");
        return "master-template";
    }

    @PostMapping
    public String searchCityPage(Model model,
                                 RestTemplate restTemplate,
                                 @RequestParam String city) {
        try {
            weatherService.getInfo(city, restTemplate, model);
            model.addAttribute("bodyContent", "gradovi");
            return "master-template";
        } catch (CityNotFoundException cityNotFoundException) {
            return "redirect:/gradovi?error=" + cityNotFoundException.getMessage();
        }
    }


}
