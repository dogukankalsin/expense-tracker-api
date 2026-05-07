package com.roadmap.expense_tracker.dto;

import com.roadmap.expense_tracker.entity.enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(@NotBlank String title,
                             String description,
                             @NotNull @Positive BigDecimal amount,
                             @NotNull ExpenseCategory category,
                             @NotNull LocalDate expenseDate) {
}
