package com.roadmap.expense_tracker.repository;

import com.roadmap.expense_tracker.entity.Expense;
import com.roadmap.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}
