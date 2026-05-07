package com.roadmap.expense_tracker.service;

import com.roadmap.expense_tracker.dto.ExpenseRequest;
import com.roadmap.expense_tracker.dto.ExpenseResponse;
import com.roadmap.expense_tracker.entity.Expense;
import com.roadmap.expense_tracker.entity.User;
import com.roadmap.expense_tracker.exception.ExpenseNotFoundException;
import com.roadmap.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    ExpenseRepository expenseRepository;
    JwtService jwtService;
    public ExpenseServiceImpl(ExpenseRepository expenseRepository,JwtService jwtService){
        this.expenseRepository=expenseRepository;
        this.jwtService=jwtService;
    }
    @Override
    public ExpenseResponse save(ExpenseRequest expenseRequest, User user) {
        Expense expense = new Expense();
        expense.setTitle(expenseRequest.title());
        expense.setDescription(expenseRequest.description());
        expense.setAmount(expenseRequest.amount());
        expense.setCategory(expenseRequest.category());
        expense.setExpenseDate(expenseRequest.expenseDate());
        expense.setUser(user);
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());
        
        Expense savedExpense = expenseRepository.save(expense);
        return convertToResponse(savedExpense);
    }

    @Override
    public ExpenseResponse findById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Girilen id ile eşleşen masraf yok."));
        return convertToResponse(expense);
    }

    @Override
    public void delete(Long id) {
        Expense expense= expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense bulunamadı."));
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseResponse> getUserExpenses(User user) {
        return expenseRepository.findByUser(user).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ExpenseResponse convertToResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getExpenseDate(),
                expense.getCreatedAt(),
                expense.getUpdatedAt(),
                expense.getUser().getId()
        );
    }
}
