package br.com.bicmsystems.clinical.domain.appointment.validations.cancellation;

import br.com.bicmsystems.clinical.domain.appointment.dto.CancellationRequest;

public interface ValidatorAppointmentCancellation {

    void validate(CancellationRequest data);

}
