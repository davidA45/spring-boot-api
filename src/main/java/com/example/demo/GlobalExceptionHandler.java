package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();


        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    String message;

                    switch (error.getField()) {

                        case "name":
                            message = "El nombre es obligatorio";
                            break;

                        case "price":
                            message = "El precio debe ser mayor que 0";
                            break;

                        default:
                            message = error.getDefaultMessage();
                    }

                    errors.put(error.getField(), message);
                });




       Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("error", "Datos inválidos");
        response.put("fields", errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
        @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(
            ProductNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("error", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

}