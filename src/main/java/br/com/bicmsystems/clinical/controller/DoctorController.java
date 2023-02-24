package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.doctor.DoctorRepository;
import br.com.bicmsystems.clinical.domain.doctor.dto.DetailDoctorResponse;
import br.com.bicmsystems.clinical.domain.doctor.dto.InsertDoctorRequest;
import br.com.bicmsystems.clinical.domain.doctor.dto.ListDoctorResponse;
import br.com.bicmsystems.clinical.domain.doctor.dto.UpdateDoctorRequest;
import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
import br.com.bicmsystems.clinical.enums.Specialty;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListDoctorResponse>> listAll(@PageableDefault(size = 5, sort = {"fullName"}) Pageable pagination) {
        var doctors = repository.findAllByActiveTrue(pagination).map(ListDoctorResponse::new);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<String>> listSpecialties() {
        var list = Arrays.stream(Specialty.values()).map(Specialty::toString).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDoctorResponse> findById(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        DetailDoctorResponse dto = new DetailDoctorResponse(model);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid InsertDoctorRequest dto, UriComponentsBuilder uriBuilder) {
        var model = new DoctorModel(dto);
        repository.save(model);
        var uri = uriBuilder
                            .path("/doctors/{id}")
                            .buildAndExpand(model.getId())
                            .toUri();
        return ResponseEntity.created(uri).body(new DetailDoctorResponse(model));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDoctorRequest dto) {
        var model = repository.getReferenceById(dto.id());
        model.updateData(dto);
        return ResponseEntity.ok(new DetailDoctorResponse(model));
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
