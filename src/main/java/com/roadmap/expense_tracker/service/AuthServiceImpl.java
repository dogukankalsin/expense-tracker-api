package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.dto.AuthResponse;
import com.roadmap.expense_tracker.dto.LoginRequest;
import com.roadmap.expense_tracker.dto.RegisterRequest;
import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    UserRepository userRp;
    @Autowired
    public AuthServiceImpl(UserRepository userRp){
        this.userRp=userRp;
    }
    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRp.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("User with given email already exist");
        }
        User user=new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        userRp.save(user);
        return new AuthResponse("Registration successful");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user=userRp.findByEmail(request.email()).orElseThrow(()-> new RuntimeException("User not found"));
        if(!user.getPassword().equals(request.password())){
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse("Login successful");
    }
}
