package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorSchedulingAdvanceTime")
public class ValidatorAdvanceTime implements ValidatorAppointmentScheduling {

    public void validate(AppointmentRequest data) {

        var dateTime = data.dateTime();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateTime).toMinutes();

        if(differenceInMinutes < 30) {
            throw new ValidationException("Appointment must be scheduled at least 30 minutes in advance");
        }

    }

}
