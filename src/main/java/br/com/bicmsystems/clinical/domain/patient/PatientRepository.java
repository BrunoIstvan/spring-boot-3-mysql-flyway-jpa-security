package br.com.bicmsystems.clinical.domain.patient;


import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    Page<PatientModel> findAllByActiveTrue(Pageable page);

    @Query("""
            select p.active from Patients p where p.id = :id
            """)
    boolean findActiveById(Long id);
}
