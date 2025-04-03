package com.tam.StudentManagement.Exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String message;
    private final int statusCode;
    private final String errorCode;

    public BaseException(String message) {
        super(message);
        this.message = message;
        this.statusCode = 400;
        this.errorCode = "BAD_REQUEST";
    }

    public BaseException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = "ERROR";
    }

    public BaseException(String message, int statusCode, String errorCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}