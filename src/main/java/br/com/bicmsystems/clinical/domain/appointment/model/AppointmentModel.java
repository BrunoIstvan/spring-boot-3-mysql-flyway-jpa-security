package br.com.bicmsystems.clinical.domain.appointment.model;

import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;
import br.com.bicmsystems.clinical.enums.ReasonCancellation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorModel doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientModel patient;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "reason_cancellation")
    @Enumerated(EnumType.STRING)
    private ReasonCancellation reason;

    public void cancel(ReasonCancellation reason) {
        this.reason = reason;
    }

}
