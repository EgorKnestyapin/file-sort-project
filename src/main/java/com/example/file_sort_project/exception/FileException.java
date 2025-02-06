package com.example.file_sort_project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FileException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public FileException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public FileException(String message) {
        super(message);
    }
}
