package br.com.bicmsystems.clinical.domain.doctor;

import br.com.bicmsystems.clinical.domain.doctor.dto.ListDoctorResponse;
import br.com.bicmsystems.clinical.enums.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Page<ListDoctorResponse> listAll(Pageable pagination, Specialty specialty) {
        Page<ListDoctorResponse> doctors = null;
        if(Objects.isNull(specialty)) {
            doctors = repository.findAllByActiveTrue(pagination).map(ListDoctorResponse::new);
        } else {
            doctors = repository.findAllByActiveTrueAndSpecialty(pagination, specialty).map(ListDoctorResponse::new);
        }
        return doctors;
    }

}
