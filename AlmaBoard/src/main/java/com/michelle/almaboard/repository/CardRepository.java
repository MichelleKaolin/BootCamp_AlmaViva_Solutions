package com.michelle.almaboard.repository;

import com.michelle.almaboard.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}