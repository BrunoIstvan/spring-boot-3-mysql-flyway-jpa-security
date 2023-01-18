package br.com.bicmsystems.clinical.domain.doctor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {

    Page<DoctorModel> findAllByActiveTrue(Pageable page);

}
