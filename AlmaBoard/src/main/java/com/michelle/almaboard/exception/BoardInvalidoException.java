package com.michelle.almaboard.exception;

public class BoardInvalidoException extends RuntimeException {

    public BoardInvalidoException(String mensagem) {
        super(mensagem);
    }
}