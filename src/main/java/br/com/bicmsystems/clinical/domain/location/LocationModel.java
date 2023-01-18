package br.com.bicmsystems.clinical.domain.location;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {

    private String address;
    private String neighborhood;

    @Column(name = "POSTALCODE")
    private String postalCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public LocationModel(LocationData dto) {
        this.address = dto.address();
        this.neighborhood = dto.neighborhood();
        this.postalCode = dto.postalCode();
        this.city = dto.city();
        this.state = dto.state();
        this.complement = dto.complement();
        this.number = dto.number();
    }

    public void updateInfos(LocationData dto) {
        if(dto.address() != null) {
            this.address = dto.address();
        }
        if(dto.neighborhood() != null) {
            this.neighborhood = dto.neighborhood();
        }
        if(dto.postalCode() != null) {
            this.postalCode = dto.postalCode();
        }
        if(dto.city() != null) {
            this.city = dto.city();
        }
        if(dto.state() != null) {
            this.state = dto.state();
        }
        if(dto.complement() != null) {
            this.complement = dto.complement();
        }
        if(dto.number() != null) {
            this.number = dto.number();
        }
    }

}
