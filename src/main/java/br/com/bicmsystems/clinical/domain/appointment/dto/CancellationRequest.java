package br.com.bicmsystems.clinical.domain.appointment.dto;

import br.com.bicmsystems.clinical.enums.ReasonCancellation;
import jakarta.validation.constraints.NotNull;

public record CancellationRequest(

        @NotNull
        Long id,

        @NotNull
        ReasonCancellation reason

) {
}
