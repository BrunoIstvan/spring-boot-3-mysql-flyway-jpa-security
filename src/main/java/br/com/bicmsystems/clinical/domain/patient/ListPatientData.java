package br.com.bicmsystems.clinical.domain.patient;

import br.com.bicmsystems.clinical.domain.location.LocationData;

public record ListPatientData(Long id, String fullName, String mail, String cpf, String phone,
                              LocationData location) {

    public ListPatientData(PatientModel model) {
        this(model.getId(), model.getFullName(), model.getMail(), model.getCpf(),
             model.getPhone(), new LocationData(model.getLocation()));
    }

}
