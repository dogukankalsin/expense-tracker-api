package com.roadmap.expense_tracker.dto;

import com.roadmap.expense_tracker.entity.enums.ExpenseCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseResponse(
        Long id,
        String title,
        String description,
        BigDecimal amount,
        ExpenseCategory category,
        LocalDate expenseDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long userId
) {
}
