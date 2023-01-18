package br.com.bicmsystems.clinical.domain.doctor;

import br.com.bicmsystems.clinical.domain.location.LocationData;
import br.com.bicmsystems.clinical.domain.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InsertDoctorData(
        @NotBlank
        String fullName,

        @NotBlank
        @Email
        String mail,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}")
        String phone,

        @NotNull
        Specialty specialty,

        @NotNull @Valid
        LocationData location) {
}
