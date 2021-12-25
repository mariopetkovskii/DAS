package com.example.weatherapplication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    String getHomePage(HttpServletRequest request){
        int year = LocalDateTime.now().getYear();
        request.setAttribute("vreme", year);
        return "home";
    }
    @GetMapping("denied_access")
    public String accessDeniedPage(Model model){
        model.addAttribute("bodyContent", "denied_access");
        return "master-template";
    }
}
