package br.com.bicmsystems.clinical.domain.doctor.dto;

import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.enums.Specialty;
import br.com.bicmsystems.clinical.domain.location.dto.LocationData;

public record DetailDoctorResponse(Long id, String fullName, String mail, String crm, String phone,
                                   Specialty specialty, LocationData location) {

    public DetailDoctorResponse(DoctorModel model) {
        this(model.getId(), model.getFullName(), model.getMail(), model.getCrm(), model.getPhone(),
                model.getSpecialty(), new LocationData(model.getLocation()));
    }
}
