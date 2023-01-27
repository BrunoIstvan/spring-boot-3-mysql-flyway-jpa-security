package br.com.bicmsystems.clinical.domain.doctor.dto;

import br.com.bicmsystems.clinical.domain.location.dto.LocationData;
import br.com.bicmsystems.clinical.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record InsertDoctorData(

        @NotBlank(message = "fullname field is required")
        String fullName,

        @NotBlank(message = "mail field is required")
        @Email(message = "Mail format is invalid")
        String mail,

        @NotBlank(message = "crm field is required")
        @Pattern(regexp = "\\d{4,6}", message = "crm format is invalid")
        String crm,

        @NotBlank(message = "phone number field is required")
        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}", message = "phone number format is invalid")
        String phone,

        @NotNull(message = "specialty field is required")
        Specialty specialty,

        @NotNull(message = "Location data is required")
        @Valid
        LocationData location) {
}
