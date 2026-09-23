package com.devbmendes.jobtrack.exceptions;

import com.devbmendes.jobtrack.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


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
    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleCompanyAlreadyExistsException(
            CompanyAlreadyExistsException exception){
        ApiResponse<Void> response = new ApiResponse<>(exception.getMessage(),null);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(
            MethodArgumentNotValidException exception
    ){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        ApiResponse<Map<String, String>> response = new ApiResponse<>(

                "Validation error",
                errors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(response);

    }
    @ExceptionHandler(UserAlreadyAssociatedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyAssociated(
            UserAlreadyAssociatedException exception) {

        ApiResponse<Void> response =
                new ApiResponse<>(exception.getMessage(), null);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

}
