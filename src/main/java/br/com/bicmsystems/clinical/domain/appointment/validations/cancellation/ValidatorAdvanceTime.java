package br.com.bicmsystems.clinical.domain.appointment.validations.cancellation;

import br.com.bicmsystems.clinical.domain.appointment.AppointmentRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.CancellationRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorCancellationAdvanceTime")
public class ValidatorAdvanceTime implements ValidatorAppointmentCancellation {

    @Autowired
    private AppointmentRepository repository;
    
    public void validate(CancellationRequest data) {

        final var model = repository.getReferenceById(data.id());
        final var now = LocalDateTime.now();
        final var differenceTime = Duration.between(now, model.getDateTime()).toHours();

        if (differenceTime < 24) {
            throw new ValidationException("Appointment can only be canceled at least 24 hours in advance");
        }

    }

}
