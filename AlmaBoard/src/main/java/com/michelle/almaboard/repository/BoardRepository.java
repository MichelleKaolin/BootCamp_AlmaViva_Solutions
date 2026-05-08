package com.michelle.almaboard.repository;

import com.michelle.almaboard.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}