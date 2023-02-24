package br.com.bicmsystems.clinical.domain.appointment.dto;

import br.com.bicmsystems.clinical.enums.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequest(

        Long idDoctor,

        Specialty specialty,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateTime

) {
}
