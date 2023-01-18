package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.doctor.DoctorModel;
import br.com.bicmsystems.clinical.domain.doctor.DoctorRepository;
import br.com.bicmsystems.clinical.domain.doctor.InsertDoctorData;
import br.com.bicmsystems.clinical.domain.patient.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @GetMapping
    public Page<ListPatientData> listAll(@PageableDefault(size = 10, sort = {"fullName"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListPatientData::new);
    }

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid InsertPatientData dto) {
        var model = new PatientModel(dto);
        repository.save(model);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientData dto) {
        var model = repository.getReferenceById(dto.id());
        model.updateInfos(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        model.delete();
    }

}
