package br.com.bicmsystems.clinical.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(

        @NotBlank(message = "login field is required")
        String login,

        @NotBlank(message = "password field is required")
        String password) {
}
