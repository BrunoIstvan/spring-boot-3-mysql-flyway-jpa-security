package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.doctor.*;
import br.com.bicmsystems.clinical.domain.doctor.dto.DetailDoctorData;
import br.com.bicmsystems.clinical.domain.doctor.dto.InsertDoctorData;
import br.com.bicmsystems.clinical.domain.doctor.dto.ListDoctorData;
import br.com.bicmsystems.clinical.domain.doctor.dto.UpdateDoctorData;
import br.com.bicmsystems.clinical.domain.doctor.model.DoctorModel;
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
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListDoctorData>> listAll(@PageableDefault(size = 5, sort = {"fullName"}) Pageable pagination) {
        var doctors = repository.findAllByActiveTrue(pagination).map(ListDoctorData::new);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDoctorData> findById(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        DetailDoctorData dto = new DetailDoctorData(model);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid InsertDoctorData dto, UriComponentsBuilder uriBuilder) {
        var model = new DoctorModel(dto);
        repository.save(model);
        var uri = uriBuilder
                            .path("/doctors/{id}")
                            .buildAndExpand(model.getId())
                            .toUri();
        return ResponseEntity.created(uri).body(new DetailDoctorData(model));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDoctorData dto) {
        var model = repository.getReferenceById(dto.id());
        model.updateData(dto);
        return ResponseEntity.ok(new DetailDoctorData(model));
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
