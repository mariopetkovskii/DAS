package com.example.weatherapplication.service.impl;

import com.example.weatherapplication.model.City;
import com.example.weatherapplication.model.Foo;
import com.example.weatherapplication.model.FooError;
import com.example.weatherapplication.model.exceptions.CityNotFoundException;
import com.example.weatherapplication.service.ErrorHandler.RestTemplateResponseErrorHandler;
import com.example.weatherapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.web.servlet.function.RequestPredicates.method;

@ContextConfiguration(classes = { CityNotFoundException.class, Foo.class })
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplateBuilder builder;

    @Autowired
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
            restTemplateBuilder
                    .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }


    @Override()
    public void getInfo(String city, RestTemplate restTemplate, Model model) {
        String resourceUrl = "http://api.weatherapi.com/v1/current.json?key=49c446e04a81488591322208212209&q=" + city + "&aqi=yes";

        restTemplate = this.builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        Foo response = restTemplate
                .getForObject(resourceUrl, Foo.class);

            model.addAttribute("items", response);
    }


}
