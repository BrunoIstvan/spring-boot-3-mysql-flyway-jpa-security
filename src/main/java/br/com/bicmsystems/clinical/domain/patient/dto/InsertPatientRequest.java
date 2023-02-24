package br.com.bicmsystems.clinical.domain.patient.dto;

import br.com.bicmsystems.clinical.domain.location.dto.LocationData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InsertPatientRequest(

        @NotBlank(message = "fullname field is required")
        String fullName,

        @NotBlank(message = "mail field is required")
        @Email(message = "Mail format is invalid")
        String mail,

        @NotBlank(message = "phone number field is required")
        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}", message = "phone number format is invalid")
        String phone,

        @NotNull(message = "cpf field is required")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "cpf format is invalid")
        String cpf,

        @NotNull (message = "Location data is required")
        @Valid
        LocationData location) {
}
