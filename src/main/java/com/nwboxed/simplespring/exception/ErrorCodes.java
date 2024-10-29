package com.nwboxed.simplespring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    ENTITY_NOT_FOUND("404", "Car Details Not Found"),
    DUPLICATE_ENTITY("409", "Care Details already present"),
    CONSTRAINT_VIOLATION_CLIENT_ERROR("400", "One or more parameters are missing or invalid"),
    INTERNAL_SERVER_ERROR("500", "Something went wrong, please try again after sometime");

    private final String code;
    private final String message;
}
