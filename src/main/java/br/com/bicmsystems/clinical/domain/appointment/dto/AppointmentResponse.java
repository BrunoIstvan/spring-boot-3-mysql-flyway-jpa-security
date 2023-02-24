package br.com.bicmsystems.clinical.domain.appointment.dto;

import br.com.bicmsystems.clinical.domain.appointment.model.AppointmentModel;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime dateTime) {

    public AppointmentResponse(AppointmentModel model) {
        this(model.getId(), model.getDoctor().getId(), model.getPatient().getId(), model.getDateTime());
    }
}
