package com.nwboxed.simplespring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CarDto(
        @NotNull(message = "'id' field is required") String id,
        @NotNull(message = "'type' field is required") String type,
        @NotNull(message = "'colour' field is required") String colour) {
}
