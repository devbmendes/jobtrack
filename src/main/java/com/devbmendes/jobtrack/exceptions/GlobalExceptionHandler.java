package com.devbmendes.jobtrack.exceptions;

import com.devbmendes.jobtrack.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException
            (ResourceNotFoundException exception){
        ApiResponse<Void> response = new ApiResponse<>(exception.getMessage(),null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception
    ){
        ApiResponse<Void> response = new ApiResponse<>(exception.getMessage(),null);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(
            MethodArgumentNotValidException exception
    ){
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ApiResponse<Void> response = new ApiResponse<>(
                message,
                null
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

}
