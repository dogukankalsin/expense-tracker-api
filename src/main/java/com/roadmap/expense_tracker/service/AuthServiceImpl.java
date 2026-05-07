package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.dto.AuthResponse;
import com.roadmap.expense_tracker.dto.LoginRequest;
import com.roadmap.expense_tracker.dto.RegisterRequest;
import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.entity.enums.Role;
import com.roadmap.expense_tracker.exception.EmailAlreadyExistsException;
import com.roadmap.expense_tracker.exception.InvalidCredentialsException;
import com.roadmap.expense_tracker.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    UserRepository userRp;
    private final PasswordEncoder passwordEncoder;
    JwtService jwtService;

    public AuthServiceImpl(UserRepository userRp, PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRp=userRp;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }
    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRp.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email zaten kullanılıyor!");
        }
        User user=new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRp.save(user);
        return new AuthResponse("Registration successful");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user=userRp.findByEmail(request.email()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return new AuthResponse(jwtService.generateToken(user.getEmail()));
    }
}
