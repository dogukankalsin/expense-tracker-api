package com.roadmap.expense_tracker.dto;

public record RegisterRequest(
        String username, String email, String password) {
}
