package com.example.weatherapplication.web.controller;


import com.example.weatherapplication.model.Role;
import com.example.weatherapplication.model.User;
import com.example.weatherapplication.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    Model model;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changeRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    private String getChangeRolePage(@RequestParam(required = false) String error,Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "changeRole");
        return "master-template";
    }

    @PutMapping("/change")
    private String setRole(@RequestParam String user,
                           @RequestParam Role role){
        userService.changeRole(user, role);
        model.addAttribute("bodyContent", "changeRole");
        System.out.println("test");
        return "master-template";
    }
}