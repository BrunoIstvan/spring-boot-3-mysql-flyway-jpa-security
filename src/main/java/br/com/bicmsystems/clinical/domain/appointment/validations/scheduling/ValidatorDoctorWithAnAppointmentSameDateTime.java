package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.appointment.AppointmentRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAnAppointmentSameDateTime implements ValidatorAppointmentScheduling {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentRequest data) {

        var hasScheduled = repository.existsByDoctorIdAndDateTimeAndReasonIsNull(data.idDoctor(), data.dateTime());
        if(hasScheduled) {
            throw new ValidationException("The doctor already has an appointment scheduled on this date time");
        }

    }

}
