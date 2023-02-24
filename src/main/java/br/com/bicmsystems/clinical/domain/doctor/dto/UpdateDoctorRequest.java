package br.com.bicmsystems.clinical.domain.doctor.dto;

import br.com.bicmsystems.clinical.domain.location.dto.LocationData;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateDoctorRequest(

        @NotNull(message = "Id is mandatory")
        Long id,

        String fullName,

        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}")
        String phone,

        LocationData location) {
}
