package com.example.demo.exception;

public class RequestViolationException extends RuntimeException {

    private static final long serialVersionUID = 1725714725557473871L;

    public RequestViolationException(String message) {
        super(message);
    }

    public RequestViolationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RequestViolationException(Throwable throwable) {
        super(throwable);
    }
}

