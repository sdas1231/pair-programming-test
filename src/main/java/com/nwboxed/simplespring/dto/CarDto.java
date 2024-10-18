package com.nwboxed.simplespring.dto;

import lombok.Builder;

@Builder
public record CarDto(String type, String colour) {
}
