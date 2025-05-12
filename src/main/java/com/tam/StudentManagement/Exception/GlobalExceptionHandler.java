package com.tam.StudentManagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getStatusCode(),
                ex.getErrorCode(),
                ex.getMessage(),
                LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Lấy thông báo lỗi đầu tiên từ danh sách lỗi
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // Tạo đối tượng ErrorResponse
        ErrorResponse error = new ErrorResponse(
                400,
                "VALIDATION_ERROR",
                errorMessage,
                LocalDateTime.now().format(formatter));

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                404,
                "NOT_FOUND",
                "The requested URL " + ex.getRequestURL() + " was not found on this server.",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse error = new ErrorResponse(
                405,
                "METHOD_NOT_ALLOWED",
                "HTTP method " + ex.getMethod() + " is not supported for this endpoint",
                LocalDateTime.now().format(formatter));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                500,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
