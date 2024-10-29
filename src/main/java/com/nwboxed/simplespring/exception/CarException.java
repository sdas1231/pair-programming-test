package com.nwboxed.simplespring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarException extends RuntimeException {
    private String code;
    private String message;
}
