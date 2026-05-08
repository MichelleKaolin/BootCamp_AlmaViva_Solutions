package com.michelle.almaboard.repository;

import com.michelle.almaboard.model.Coluna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColunaRepository extends JpaRepository<Coluna, Long> {
}