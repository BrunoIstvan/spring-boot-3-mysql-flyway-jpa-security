package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.patient.PatientRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorPatientActive implements ValidatorAppointmentScheduling {

    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentRequest data) {

        if(Objects.isNull(data.idPatient())) {
            return;
        }

        var active = repository.findActiveById(data.idPatient());
        if(!active) {
            throw new ValidationException("Is not possible do schedule an appointment to an inactive patient");
        }

    }

}
