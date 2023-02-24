package br.com.bicmsystems.clinical.domain.appointment.validations;

import br.com.bicmsystems.clinical.domain.appointment.AppointmentRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.CancellationRequest;
import br.com.bicmsystems.clinical.domain.appointment.validations.cancellation.ValidatorAppointmentCancellation;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentCancellationService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<ValidatorAppointmentCancellation> validators;

    public void cancelAppointment(CancellationRequest data) {
        validateCancelAppointmentRequest(data);

        validators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.id());
        appointment.cancel(data.reason());
    }

    private void validateCancelAppointmentRequest(CancellationRequest data) {
        if(!appointmentRepository.existsById(data.id())) {
            throw new ValidationException("Appointment id informed not found");
        }
    }

}
