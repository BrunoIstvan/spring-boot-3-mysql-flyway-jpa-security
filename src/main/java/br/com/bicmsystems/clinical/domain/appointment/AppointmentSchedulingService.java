package br.com.bicmsystems.clinical.domain.appointment;

import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentDetailedResponse;
import br.com.bicmsystems.clinical.domain.doctor.DoctorRepository;
import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.domain.patient.PatientRepository;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentResponse;
import br.com.bicmsystems.clinical.domain.appointment.model.AppointmentModel;
import br.com.bicmsystems.clinical.domain.appointment.validations.scheduling.ValidatorAppointmentScheduling;
import br.com.bicmsystems.clinical.infra.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AppointmentSchedulingService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private List<ValidatorAppointmentScheduling> validators;

    public AppointmentResponse schedule(AppointmentRequest data) {

        validateAppointmentScheduleRequest(data);

        validators.forEach(v -> v.validate(data));

        var patientModel = patientRepository.getReferenceById(data.idPatient());
        var doctorModel = chooseDoctor(data);
        if(Objects.isNull(doctorModel)) {
            throw new ValidationException("There are no doctors available on this date time");
        }

        var model = new AppointmentModel(null, doctorModel, patientModel, data.dateTime(), null);
        appointmentRepository.save(model);

        return new AppointmentResponse(model);

    }

    private DoctorModel chooseDoctor(AppointmentRequest data) {

        if(Objects.nonNull(data.idDoctor())) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if(Objects.isNull(data.specialty())) {
            throw new ValidationException("Specialty must be informed when doctor id is not informed");
        }

        return doctorRepository.chooseDoctorBySpecialtyFreeSchedule(data.specialty(), data.dateTime());

    }

    private void validateAppointmentScheduleRequest(AppointmentRequest data) throws ValidationException {

        if(!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("patient id informed does not exists");
        }

        if(Objects.nonNull(data.idDoctor()) && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("doctor id informed does not exists");
        }

    }

    public Page<AppointmentDetailedResponse> query(Long idDoctor, Long idPatient, Pageable pageable) {

        if(Objects.isNull(idDoctor) && Objects.isNull(idPatient)) {
            throw new ValidationException("Please, inform idDoctor or idPatient");
        }

        if(Objects.nonNull(idDoctor) && Objects.isNull(idPatient)) {
            return appointmentRepository.findAllByDoctorIdAndReasonIsNull(idDoctor, pageable).map(AppointmentDetailedResponse::new);
        }

        if(Objects.nonNull(idPatient) && Objects.isNull(idDoctor)) {
            return appointmentRepository.findAllByPatientIdAndReasonIsNull(idPatient, pageable).map(AppointmentDetailedResponse::new);
        }

        return appointmentRepository.findAllByPatientIdAndDoctorIdAndReasonIsNull(idPatient, idDoctor, pageable).map(AppointmentDetailedResponse::new);
    }

}
