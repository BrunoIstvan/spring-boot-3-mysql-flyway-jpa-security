package br.com.bicmsystems.clinical.controller;

import br.com.bicmsystems.clinical.domain.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping
    public Page<ListDoctorData> listAll(@PageableDefault(size = 10, sort = {"fullName"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListDoctorData::new);
    }

    @GetMapping("/{id}")
    public DetailDoctorData findById(@PathVariable("id") Long id) {
        var model = repository.getReferenceById(id);
        DetailDoctorData dto = new DetailDoctorData(model);
        return dto;
    }

    @PostMapping
    @Transactional
    public void save(@RequestBody @Valid InsertDoctorData dto) {
        var model = new DoctorModel(dto);
        repository.save(model);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctorData dto) {
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
