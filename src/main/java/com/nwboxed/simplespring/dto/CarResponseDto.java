package com.nwboxed.simplespring.dto;

import lombok.Builder;

@Builder
public record CarResponseDto(String id, String type, String colour) {
}
