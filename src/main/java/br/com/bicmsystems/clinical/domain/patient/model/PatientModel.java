package br.com.bicmsystems.clinical.domain.patient.model;

import br.com.bicmsystems.clinical.domain.location.model.LocationModel;
import br.com.bicmsystems.clinical.domain.patient.dto.InsertPatientData;
import br.com.bicmsystems.clinical.domain.patient.dto.UpdatePatientData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULLNAME")
    private String fullName;

    private String mail;
    private String cpf;
    private String phone;

    @Embedded
    private LocationModel location;

    private Boolean active;

    public PatientModel(InsertPatientData dto) {
        this.fullName = dto.fullName();
        this.mail = dto.mail();
        this.cpf = dto.cpf();
        this.phone = dto.phone();
        this.location = new LocationModel(dto.location());
        this.active = true;
    }

    public void updateData(UpdatePatientData dto) {
        if(dto.fullName() != null)
            this.fullName = dto.fullName();

        if(dto.mail() != null)
            this.mail = dto.mail();

        if(dto.phone() != null)
            this.phone = dto.phone();

        if(dto.location() != null)
            this.location = new LocationModel(dto.location());
    }

    public void delete() {
        this.active = false;
    }

}
