package br.com.bicmsystems.clinical.domain.doctor;

import br.com.bicmsystems.clinical.domain.enums.Specialty;

public record ListDoctorData(Long id, String fullName, String mail, String crm, String phone, Specialty specialty) {

    public ListDoctorData(DoctorModel model) {
        this(model.getId(), model.getFullName(), model.getMail(), model.getCrm(), model.getPhone(), model.getSpecialty());
    }
}
