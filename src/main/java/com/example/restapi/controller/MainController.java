package com.example.restapi.controller;

import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import com.example.restapi.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String main() {
        return "login";
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/getUsers")
    public String getUsersDetails() {
        return "getUsers";
    }

    @GetMapping("/add")
    public String add() {
        return "register";
    }


}
