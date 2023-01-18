package br.com.bicmsystems.clinical.domain.patient;

import br.com.bicmsystems.clinical.domain.location.LocationData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InsertPatientData(
        @NotBlank
        String fullName,

        @NotBlank
        @Email
        String mail,

        @NotBlank
        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}")
        String phone,

        @NotNull
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull @Valid
        LocationData location) {
}
