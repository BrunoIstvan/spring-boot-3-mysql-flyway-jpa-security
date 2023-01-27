package br.com.bicmsystems.clinical.domain.patient.dto;

import br.com.bicmsystems.clinical.domain.location.dto.LocationData;
import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;

public record DetailPatientData(Long id, String fullName, String mail, String cpf, String phone,
                                LocationData location) {

    public DetailPatientData(PatientModel model) {
        this(model.getId(),
                model.getFullName(),
                model.getMail(),
                model.getCpf(),
                model.getPhone(),
                new LocationData(model.getLocation()));
    }
}
