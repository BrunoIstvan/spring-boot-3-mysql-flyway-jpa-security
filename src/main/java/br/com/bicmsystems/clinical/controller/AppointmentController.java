package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.appointment.AppointmentSchedulingService;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentDetailedResponse;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentRequest;
import br.com.bicmsystems.clinical.domain.appointment.dto.AppointmentResponse;
import br.com.bicmsystems.clinical.domain.appointment.dto.CancellationRequest;
import br.com.bicmsystems.clinical.domain.appointment.validations.AppointmentCancellationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedulingService appointmentSchedulingService;

    @Autowired
    private AppointmentCancellationService appointmentCancellationService;

    @GetMapping
    public ResponseEntity<Page<AppointmentDetailedResponse>> query(
        @RequestParam(required = false) Long idDoctor, @RequestParam(required = false) Long idPatient, Pageable page
    ) {
        var response = appointmentSchedulingService.query(idDoctor, idPatient, page);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentResponse> schedule(@Valid @RequestBody AppointmentRequest data) {

        var response = appointmentSchedulingService.schedule(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<AppointmentResponse> cancelScheduling(@Valid @RequestBody CancellationRequest data) {

        appointmentCancellationService.cancelAppointment(data);
        return ResponseEntity.noContent().build();
    }

}
