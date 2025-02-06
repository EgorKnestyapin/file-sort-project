package com.example.file_sort_project.exception.handler;

import com.example.file_sort_project.exception.FileException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Hidden
@RestControllerAdvice
public class AppExceptionHandler {

    private static final String ERROR_LOG_TEMPLATE = "{} {} {}";

    @ExceptionHandler(FileException.class)
    public ResponseEntity<String> handleFileException(FileException e) {
        log.error(ERROR_LOG_TEMPLATE, e.getStatus(), e.getMessage(), e.getStackTrace());
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(ERROR_LOG_TEMPLATE, HttpStatus.BAD_REQUEST, e.getMessage(), e.getStackTrace());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
