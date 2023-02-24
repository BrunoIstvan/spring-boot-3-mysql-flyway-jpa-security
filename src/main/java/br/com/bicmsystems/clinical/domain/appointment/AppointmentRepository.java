package br.com.bicmsystems.clinical.domain.appointment;

import br.com.bicmsystems.clinical.domain.appointment.model.AppointmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    boolean existsByDoctorIdAndDateTimeAndReasonIsNull(Long idDoctor, LocalDateTime dateTime);

    boolean existsByPatientIdAndDateTimeBetweenAndReasonIsNull(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);

    Page<AppointmentModel> findAllByDoctorIdAndReasonIsNull(Long id, Pageable pageable);

    Page<AppointmentModel> findAllByPatientIdAndReasonIsNull(Long id, Pageable pageable);

    Page<AppointmentModel> findAllByPatientIdAndDoctorIdAndReasonIsNull(Long patientId, Long doctorId, Pageable pageable);
}
