package br.com.bicmsystems.clinical.domain.doctor.dto;

import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.enums.Specialty;

public record ListDoctorData(Long id,
                             String fullName,
                             String mail,
                             String crm,
                             String phone,
                             Specialty specialty) {

    public ListDoctorData(DoctorModel model) {
        this(model.getId(), model.getFullName(), model.getMail(), model.getCrm(), model.getPhone(), model.getSpecialty());
    }
}
