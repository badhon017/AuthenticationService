package com.JwtAuthentication.jwtAuthService.data;

import lombok.Builder;

@Builder
public record JwtResponseDTO(String jwt,
                             String username) {
}
