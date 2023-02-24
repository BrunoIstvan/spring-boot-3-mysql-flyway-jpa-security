package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;

public interface ValidatorAppointmentScheduling {

    void validate(AppointmentRequest data);

}
