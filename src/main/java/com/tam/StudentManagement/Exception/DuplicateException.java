package com.tam.StudentManagement.Exception;

public class DuplicateException extends BaseException {
    public DuplicateException(String message) {
        super(message, 409, "DUPLICATE");
    }
}