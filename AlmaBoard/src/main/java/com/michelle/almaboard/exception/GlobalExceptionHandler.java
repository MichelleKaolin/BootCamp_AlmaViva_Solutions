package com.michelle.almaboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardInvalidoException.class)
    public ResponseEntity<Map<String, String>> tratarBoard(
            BoardInvalidoException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "erro",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(FluxoInvalidoException.class)
    public ResponseEntity<Map<String, String>> tratarFluxo(
            FluxoInvalidoException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "erro",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(CardBloqueadoException.class)
    public ResponseEntity<Map<String, String>> tratarBloqueio(
            CardBloqueadoException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "erro",
                        ex.getMessage()
                ));
    }
}