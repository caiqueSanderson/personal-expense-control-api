package com.infnet.personal_expense_control_api.model.repository;

import com.infnet.personal_expense_control_api.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategoryIgnoreCase(String category);
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
}
