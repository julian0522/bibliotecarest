package com.jguarnizo.bibliotecarest.configurations;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jguarnizo.bibliotecarest.exceptions.BookExistException;
import com.jguarnizo.bibliotecarest.exceptions.BookNotFoundException;
import com.jguarnizo.bibliotecarest.model.dtos.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(BookNotFoundException.class)
        public ResponseEntity<ApiError> handleBookNotFound(
                        BookNotFoundException ex,
                        HttpServletRequest request) {

                ApiError error = new ApiError(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                request.getRequestURI());

                logger.warn(
                                "Libro no encontrado | URI: {} | Message: {}",
                                request.getRequestURI(),
                                ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(BookExistException.class)
        public ResponseEntity<ApiError> handleBookExist(
                        BookExistException ex,
                        HttpServletRequest request) {

                ApiError error = new ApiError(
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage(),
                                request.getRequestURI());

                logger.warn(
                                "Libro ya existe | URI: {} | Message: {}",
                                request.getRequestURI(),
                                ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleGenericException(
                        Exception ex,
                        HttpServletRequest request) {

                ApiError error = new ApiError(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage(),
                                request.getRequestURI());

                logger.error(
                                "Unhandled exception | URI: {} | Method: {}",
                                request.getRequestURI(),
                                request.getMethod(),
                                ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                logger.warn(
                                "Validation failed | URI: {} | Errors: {}",
                                request.getRequestURI(),
                                errors);
                return ResponseEntity.badRequest().body(errors);
        }
}
