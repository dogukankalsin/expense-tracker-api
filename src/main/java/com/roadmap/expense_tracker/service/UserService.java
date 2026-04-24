package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User update(Long id, User user);
    void delete(Long id);
}
