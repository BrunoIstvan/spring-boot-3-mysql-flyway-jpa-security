package br.com.bicmsystems.clinical.domain.patient.dto;

import br.com.bicmsystems.clinical.domain.location.dto.LocationData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdatePatientRequest(

        @NotNull(message = "Id is mandatory")
        Long id,

        String fullName,

        @Email(message = "Mail format is invalid")
        String mail,

        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}")
        String phone,

        LocationData location) {
}
