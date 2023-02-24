package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.patient.*;
import br.com.bicmsystems.clinical.domain.patient.dto.DetailPatientResponse;
import br.com.bicmsystems.clinical.domain.patient.dto.InsertPatientRequest;
import br.com.bicmsystems.clinical.domain.patient.dto.ListPatientResponse;
import br.com.bicmsystems.clinical.domain.patient.dto.UpdatePatientRequest;
import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListPatientResponse>> listAll(@PageableDefault(size = 5, sort = {"fullName"}) Pageable pagination) {
        var patients = repository.findAllByActiveTrue(pagination).map(ListPatientResponse::new);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPatientResponse> findById(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        DetailPatientResponse dto = new DetailPatientResponse(model);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid InsertPatientRequest dto, UriComponentsBuilder uriBuilder) {
        var model = new PatientModel(dto);
        repository.save(model);
        var uri = uriBuilder
                .path("/patients/{id}")
                .buildAndExpand(model.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DetailPatientResponse(model));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePatientRequest dto) {
        var model = repository.getReferenceById(dto.id());
        model.updateData(dto);
        return ResponseEntity.ok(new DetailPatientResponse(model));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        model.delete();
        return ResponseEntity.noContent().build();
    }

}
