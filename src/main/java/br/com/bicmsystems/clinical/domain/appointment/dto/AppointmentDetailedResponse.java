package br.com.bicmsystems.clinical.domain.appointment.dto;

import br.com.bicmsystems.clinical.domain.appointment.model.AppointmentModel;
import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;
import br.com.bicmsystems.clinical.enums.Specialty;

import java.time.LocalDateTime;


public record AppointmentDetailedResponse(
        Long id,
        AppointmentDoctorDetailResponse doctor,
        AppointmentPatientDetailResponse patient,
        LocalDateTime dateTime) {

    public AppointmentDetailedResponse(AppointmentModel model) {
        this(model.getId(),
                new AppointmentDoctorDetailResponse(model.getDoctor()),
                new AppointmentPatientDetailResponse(model.getPatient()),
            model.getDateTime());
    }
}

record AppointmentDoctorDetailResponse (Long id, String fullName, String crm, String phone, Specialty specialty) {
    AppointmentDoctorDetailResponse(DoctorModel model) {
        this(model.getId(), model.getFullName(), model.getCrm(), model.getPhone(), model.getSpecialty());
    }
}

record AppointmentPatientDetailResponse(Long id, String fullName, String cpf, String phone) {
    AppointmentPatientDetailResponse(PatientModel model) {
        this(model.getId(), model.getFullName(), model.getCpf(), model.getPhone());
    }
}