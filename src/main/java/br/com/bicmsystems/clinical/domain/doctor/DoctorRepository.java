package br.com.bicmsystems.clinical.domain.doctor;


import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.enums.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {

    Page<DoctorModel> findAllByActiveTrue(Pageable page);

    Page<DoctorModel> findAllByActiveTrueAndSpecialty(Pageable page, Specialty specialty);

    @Query("""
            select d from Doctor d where d.active = true and d.specialty = :specialty
            and d.id not in (select a.doctor.id from Appointments a where a.dateTime = :dateTime and a.reason is null)
            order by rand()
            limit 1
            """)
    DoctorModel chooseDoctorBySpecialtyFreeSchedule(Specialty specialty, LocalDateTime dateTime);

    @Query("""
            select d.active from Doctor d where d.id = :id
            """)
    boolean findActiveById(Long id);


}
