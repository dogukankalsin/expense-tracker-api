package com.roadmap.expense_tracker.controller;

import com.roadmap.expense_tracker.dto.AuthResponse;
import com.roadmap.expense_tracker.dto.LoginRequest;
import com.roadmap.expense_tracker.dto.RegisterRequest;
import com.roadmap.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest request){
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request){
        return authService.login(request);
    }

}
