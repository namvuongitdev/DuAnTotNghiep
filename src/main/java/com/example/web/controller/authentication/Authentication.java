package com.example.web.controller.authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Authentication {

    @GetMapping(value = "/login")
    public String adminLogin() {
        return "login/login";
    }

}
