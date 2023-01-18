package br.com.bicmsystems.clinical.domain.patient;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    Page<PatientModel> findAllByActiveTrue(Pageable page);

}
