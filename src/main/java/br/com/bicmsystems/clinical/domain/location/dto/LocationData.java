package br.com.bicmsystems.clinical.domain.location.dto;

import br.com.bicmsystems.clinical.domain.location.model.LocationModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationData(
        @NotBlank(message = "address field is required")
        String address,

        @NotBlank(message = "neighborhood field is required")
        String neighborhood,

        @NotBlank(message = "postal code field is required")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "postal code format is invalid")
        String postalCode,

        @NotBlank(message = "city field is required")
        String city,

        @NotBlank(message = "state field is required")
        String state,

        String complement,

        String number) {

    public LocationData(LocationModel model) {
        this(model.getAddress(),
                model.getNeighborhood(),
                model.getPostalCode(),
                model.getCity(),
                model.getState(),
                model.getComplement(),
                model.getNumber());
    }
}
