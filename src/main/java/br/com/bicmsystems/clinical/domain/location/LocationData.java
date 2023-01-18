package br.com.bicmsystems.clinical.domain.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationData(
        @NotBlank
        String address,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String state,

        String complement,

        String number) {

    public LocationData(LocationModel model) {
        this(model.getAddress(), model.getNeighborhood(), model.getPostalCode(), model.getCity(),
                model.getState(), model.getComplement(), model.getNumber() );
    }
}
