package com.example.userservice.controller;


import com.example.userservice.model.User;
import com.example.userservice.model.exceptions.InvalidUserCredentialException;
import com.example.userservice.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest req, Model model){
        User user;
        try{
            user = this.authService.login(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUserCredentialException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error",exception.getMessage());
            return "login";
        }
    }
}