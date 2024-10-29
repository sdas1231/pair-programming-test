package com.nwboxed.simplespring.exception.handler;

import com.nwboxed.simplespring.dto.ErrorResponseDto;
import com.nwboxed.simplespring.exception.CarException;
import com.nwboxed.simplespring.exception.ErrorCodes;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        log.error(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        var errorBuilder = ErrorResponseDto.builder()
                .message(ErrorCodes.CONSTRAINT_VIOLATION_CLIENT_ERROR.getMessage()
                        .concat(" - ")
                        .concat(Objects.requireNonNull(
                                ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())));
        return ResponseEntity.badRequest().body(errorBuilder
                .code(ErrorCodes.CONSTRAINT_VIOLATION_CLIENT_ERROR.getCode()).build());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleOtherException(Exception ex) {
        log.error(ex.getMessage());
        var errorBuilder = ErrorResponseDto.builder().message(ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
        return ResponseEntity.internalServerError().body(errorBuilder
                .code(ErrorCodes.INTERNAL_SERVER_ERROR.getCode()).build());
    }

    @ExceptionHandler(CarException.class)
    private ResponseEntity<?> handleCarException(CarException ex) {
        log.error(ex.getMessage());
        var errorBuilder = ErrorResponseDto.builder().message(ex.getMessage());
        return ResponseEntity.status(Integer.parseInt(ex.getCode()))
                .body(errorBuilder.code(ex.getCode()).build());
    }
}
