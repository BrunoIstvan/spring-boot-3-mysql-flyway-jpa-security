package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.patient.*;
import br.com.bicmsystems.clinical.domain.patient.dto.DetailPatientData;
import br.com.bicmsystems.clinical.domain.patient.dto.InsertPatientData;
import br.com.bicmsystems.clinical.domain.patient.dto.ListPatientData;
import br.com.bicmsystems.clinical.domain.patient.dto.UpdatePatientData;
import br.com.bicmsystems.clinical.domain.patient.model.PatientModel;
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
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListPatientData>> listAll(@PageableDefault(size = 5, sort = {"fullName"}) Pageable pagination) {
        var patients = repository.findAllByActiveTrue(pagination).map(ListPatientData::new);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPatientData> findById(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        DetailPatientData dto = new DetailPatientData(model);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid InsertPatientData dto, UriComponentsBuilder uriBuilder) {
        var model = new PatientModel(dto);
        repository.save(model);
        var uri = uriBuilder
                .path("/patients/{id}")
                .buildAndExpand(model.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DetailPatientData(model));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePatientData dto) {
        var model = repository.getReferenceById(dto.id());
        model.updateData(dto);
        return ResponseEntity.ok(new DetailPatientData(model));
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
