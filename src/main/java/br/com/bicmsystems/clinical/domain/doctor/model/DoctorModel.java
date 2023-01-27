package br.com.bicmsystems.clinical.domain.doctor.model;

import br.com.bicmsystems.clinical.domain.doctor.dto.InsertDoctorData;
import br.com.bicmsystems.clinical.domain.doctor.dto.UpdateDoctorData;
import br.com.bicmsystems.clinical.enums.Specialty;
import br.com.bicmsystems.clinical.domain.location.model.LocationModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULLNAME")
    private String fullName;

    private String mail;
    private String crm;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private LocationModel location;

    private Boolean active;

    public DoctorModel(InsertDoctorData dto) {
        this.fullName = dto.fullName();
        this.mail = dto.mail();
        this.crm = dto.crm();
        this.phone = dto.phone();
        this.specialty = dto.specialty();
        this.location = new LocationModel(dto.location());
        this.active = true;
    }

    public void updateData(UpdateDoctorData dto) {
        if (dto.fullName() != null) {
            this.fullName = dto.fullName();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
        if (dto.location() != null) {
            this.location.updateData(dto.location());
        }
    }

    public void delete() {
        this.active = false;
    }

}