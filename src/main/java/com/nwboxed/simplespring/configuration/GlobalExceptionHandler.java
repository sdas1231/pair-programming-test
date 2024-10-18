package com.nwboxed.simplespring.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<?> handleNotFoundException(ResponseStatusException ex) {

        if (ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value())
            return ResponseEntity.notFound().build();
        if (ex.getStatusCode().value() == HttpStatus.BAD_REQUEST.value())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleOtherException(Exception ex) {
        return ResponseEntity.internalServerError().build();
    }
}
