package br.com.bicmsystems.clinical.domain.doctor;

import br.com.bicmsystems.clinical.domain.location.LocationData;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateDoctorData(

        @NotNull
        Long id,
        String fullName,
        @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}")
        String phone,
        LocationData location) {
}
