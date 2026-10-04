package com.campuscollab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.campuscollab.dto.LoginRequest;
import com.campuscollab.dto.LoginResponse;
import com.campuscollab.dto.RegisterRequest;
import com.campuscollab.entity.User;
import com.campuscollab.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    
}