package com.example.studyjwt.global.exception;

public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException() {}
    public JwtExpiredException(String message) {
        super(message);
    }
}
