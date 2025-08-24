package com.infnet.personal_expense_control_api.service;

import com.infnet.personal_expense_control_api.model.Expense;
import com.infnet.personal_expense_control_api.model.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public List<Expense> list(String category, LocalDate startDate, LocalDate endDate){
        if(category != null && !category.isBlank()){
            return repo.findByCategoryIgnoreCase(category);
        }
        if (startDate != null && endDate != null) {
            return repo.findByDateBetween(startDate, endDate);
        }
        return repo.findAll();
    }

    public Expense getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Expense not found"));
    }

    public Expense create(Expense e) { return repo.save(e); }

    public Expense update(Long id, Expense e) {
        Expense current = getOrThrow(id);
        current.setDescription(e.getDescription());
        current.setCategory(e.getCategory());
        current.setValue(e.getValue());
        current.setDate(e.getDate());
        return repo.save(current);
    }

    public void delete(Long id) { repo.delete(getOrThrow(id)); }
}
