package com.nwboxed.simplespring.dto;

import lombok.Builder;

@Builder
public record ErrorResponseDto(String code, String message) {
}
