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
        PatientDTO ubs = patientService.findById(id);
        return ResponseEntity.ok().body(ubs);
    }

//    @PostMapping
//    ResponseEntity<UbsDTO> insertNewUbs(@RequestBody UbsDTO ubsDTO){
//        ubsDTO = patientService.insert(ubsDTO);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ubsDTO.getId()).toUri();
//        return ResponseEntity.created(uri).body(ubsDTO);
//    }
//
//    @PutMapping(value = "/{id}")
//    ResponseEntity<UbsDTO> updateUbs(@PathVariable Long id, @RequestBody UbsDTO ubsDTO){
//        ubsDTO = patientService.update(id, ubsDTO);
//        return ResponseEntity.ok().body(ubsDTO);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        patientService.delete(id);
//
//        return ResponseEntity.noContent().build();
//    }
}
