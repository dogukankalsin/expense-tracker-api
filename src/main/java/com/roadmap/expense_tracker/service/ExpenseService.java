package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.dto.ExpenseRequest;
import com.roadmap.expense_tracker.dto.ExpenseResponse;
import com.roadmap.expense_tracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {
    ExpenseResponse save(ExpenseRequest expenseRequest, User user);
    ExpenseResponse findById(Long id);
    void delete(Long id);
    List<ExpenseResponse> getUserExpenses(User user);
}
