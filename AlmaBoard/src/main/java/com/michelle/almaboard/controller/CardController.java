package com.michelle.almaboard.controller;

import com.michelle.almaboard.dto.CardDTO;
import com.michelle.almaboard.model.Card;
import com.michelle.almaboard.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Card> criar(
            @RequestBody CardDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Card>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }

    @PutMapping("/{id}/mover/{colunaId}")
    public ResponseEntity<Card> mover(
            @PathVariable Long id,
            @PathVariable Long colunaId
    ) {

        return ResponseEntity.ok(
                service.mover(id, colunaId)
        );
    }

    @PutMapping("/{id}/bloquear")
    public ResponseEntity<Card> bloquear(
            @PathVariable Long id,
            @RequestParam String motivo
    ) {

        return ResponseEntity.ok(
                service.bloquear(id, motivo)
        );
    }

    @PutMapping("/{id}/desbloquear")
    public ResponseEntity<Card> desbloquear(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.desbloquear(id)
        );
    }
}