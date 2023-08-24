package br.com.jonatas.sectorservice.error.exceptions;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException{
    private final int code;

    public ConflictException(String message) {
        super(message);
        this.code = 409;
    }
}
