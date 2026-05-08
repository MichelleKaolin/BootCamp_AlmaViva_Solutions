package com.michelle.almaboard.service;

import com.michelle.almaboard.model.Coluna;
import com.michelle.almaboard.repository.ColunaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColunaService {

    private final ColunaRepository repository;

    public ColunaService(ColunaRepository repository) {
        this.repository = repository;
    }

    public Coluna criar(Coluna coluna) {
        return repository.save(coluna);
    }

    public List<Coluna> listar() {
        return repository.findAll();
    }
}