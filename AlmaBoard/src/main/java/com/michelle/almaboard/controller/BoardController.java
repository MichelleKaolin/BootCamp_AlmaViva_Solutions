package com.michelle.almaboard.controller;

import com.michelle.almaboard.dto.BoardDTO;
import com.michelle.almaboard.model.Board;
import com.michelle.almaboard.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Board> criar(
            @RequestBody BoardDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Board>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }
}