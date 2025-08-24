package com.infnet.personal_expense_control_api.controller;

import com.infnet.personal_expense_control_api.model.Expense;
import com.infnet.personal_expense_control_api.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Expense>> list(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return ResponseEntity.ok(service.list(category, startDate, endDate));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Expense> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getOrThrow(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> create(@Valid @RequestBody Expense expense) {
        Expense saved = service.create(expense);
        return ResponseEntity.created(URI.create("/expenses/" + saved.getId())).body(saved);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResponseEntity<Expense> update (@PathVariable Long id, @Valid @RequestBody Expense expense){
        try{
            return ResponseEntity.ok(service.update(id, expense));
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
                return ResponseEntity.notFound().build();
        }
    }
}