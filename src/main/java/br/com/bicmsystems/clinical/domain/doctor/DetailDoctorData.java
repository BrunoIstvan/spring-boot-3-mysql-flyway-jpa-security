package br.com.bicmsystems.clinical.domain.doctor;

import br.com.bicmsystems.clinical.domain.enums.Specialty;
import br.com.bicmsystems.clinical.domain.location.LocationData;

public record DetailDoctorData(Long id, String fullName, String mail, String crm, String phone,
                               Specialty specialty, LocationData location) {

    public DetailDoctorData(DoctorModel model) {
        this(model.getId(), model.getFullName(), model.getMail(), model.getCrm(), model.getPhone(),
                model.getSpecialty(), new LocationData(model.getLocation()));
    }
}
