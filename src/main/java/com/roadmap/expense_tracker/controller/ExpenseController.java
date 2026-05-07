package com.roadmap.expense_tracker.controller;

import com.roadmap.expense_tracker.dto.ExpenseRequest;
import com.roadmap.expense_tracker.dto.ExpenseResponse;
import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ExpenseResponse createExpense(@Valid @RequestBody ExpenseRequest expenseRequest,
                                         Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return expenseService.save(expenseRequest, user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ExpenseResponse> getUserExpenses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return expenseService.getUserExpenses(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.delete(id);
    }
}