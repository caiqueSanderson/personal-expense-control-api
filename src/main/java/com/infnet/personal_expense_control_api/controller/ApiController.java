package com.infnet.personal_expense_control_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/")
    public Map<String, String> help() {
        Map<String, String> endpoints = new HashMap<>();

        endpoints.put("GET /expenses", "Lista todas as despesas");
        endpoints.put("GET /expenses/{id}", "Busca uma despesa específica");
        endpoints.put("POST /expenses", "Cria uma nova despesa");
        endpoints.put("PUT /expenses/{id}", "Atualiza uma despesa existente");
        endpoints.put("DELETE /expenses/{id}", "Remove uma despesa");

        return endpoints;
    }
}
