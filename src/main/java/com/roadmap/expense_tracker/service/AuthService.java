package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.dto.AuthResponse;
import com.roadmap.expense_tracker.dto.LoginRequest;
import com.roadmap.expense_tracker.dto.RegisterRequest;
import com.roadmap.expense_tracker.entity.User;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
