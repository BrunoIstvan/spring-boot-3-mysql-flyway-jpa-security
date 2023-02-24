package br.com.bicmsystems.clinical.domain.appointment.validations.scheduling;

import br.com.bicmsystems.clinical.domain.doctor.DoctorRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidatorDoctorActive implements ValidatorAppointmentScheduling {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentRequest data) {

        if(Objects.isNull(data.idDoctor())) {
            return;
        }

        var active = doctorRepository.findActiveById(data.idDoctor());
        if(!active) {
            throw new ValidationException("Is not possible do schedule an appointment with an inactive doctor");
        }

    }

}
