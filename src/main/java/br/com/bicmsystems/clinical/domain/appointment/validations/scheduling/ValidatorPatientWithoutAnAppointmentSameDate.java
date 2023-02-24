package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.appointment.AppointmentRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientWithoutAnAppointmentSameDate implements ValidatorAppointmentScheduling {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentRequest data) {

        var firstTime = data.dateTime().withHour(7);
        var lastTime = data.dateTime().withHour(18);
        var hasScheduled = repository.existsByPatientIdAndDateTimeBetweenAndReasonIsNull(data.idPatient(), firstTime, lastTime);
        if(hasScheduled) {
            throw new ValidationException("The patient already has an appointment scheduled on this date");
        }

    }

}
