package com.tedtalk.exception;

public class RequestConsistencyException extends RuntimeException {
    public RequestConsistencyException(String message) {
        super(message);
    }
    public RequestConsistencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
