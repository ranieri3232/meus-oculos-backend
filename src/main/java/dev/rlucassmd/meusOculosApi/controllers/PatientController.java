package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.PatientDTO;
import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    ResponseEntity<Page<PatientDTO>> getAllPatientsPaged(Pageable pageable){
        Page<PatientDTO> page = patientService.findAllPatientsPaged(pageable);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        PatientDTO entity = patientService.findById(id);
        return ResponseEntity.ok().body(entity);
    }
    @GetMapping(value = "/search")
    ResponseEntity<Page<PatientDTO>> getPatientByCpf(@RequestParam String searchTerm, Pageable pageable){
        Page<PatientDTO> entity = patientService.findBySearchTerm(searchTerm, pageable);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    ResponseEntity<PatientDTO> insertNewPatient(@RequestBody PatientDTO dto){
        dto = patientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto){
        dto = patientService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
