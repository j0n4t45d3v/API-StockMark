package br.com.jonatas.sectorservice.error.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final int code;

    public NotFoundException(String message) {
        super(message);
        this.code = 404;
    }
}
