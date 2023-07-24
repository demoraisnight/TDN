package com.vinicius.cinema.services.exceptions;

public class IngressosEsgotadosException extends RuntimeException {
    public IngressosEsgotadosException(String msg) {
        super(msg);
    }
}
