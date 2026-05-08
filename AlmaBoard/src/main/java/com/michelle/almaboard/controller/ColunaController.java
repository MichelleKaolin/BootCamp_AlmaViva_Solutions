package com.michelle.almaboard.controller;

import com.michelle.almaboard.model.Coluna;
import com.michelle.almaboard.service.ColunaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colunas")
public class ColunaController {

    private final ColunaService service;

    public ColunaController(ColunaService service) {
        this.service = service;
    }

    @PostMapping
    public Coluna criar(@RequestBody Coluna coluna) {
        return service.criar(coluna);
    }

    @GetMapping
    public List<Coluna> listar() {
        return service.listar();
    }
}