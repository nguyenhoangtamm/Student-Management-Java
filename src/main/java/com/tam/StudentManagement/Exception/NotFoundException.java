package com.tam.StudentManagement.Exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, 404, "NOT_FOUND");
    }
}