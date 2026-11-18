package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.EyeExamDTO;
import dev.rlucassmd.meusOculosApi.services.EyeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/exams")
public class EyeExamController {

    @Autowired
    private EyeExamService eyeExamService;


    @GetMapping
    public ResponseEntity<Page<EyeExamDTO>> findAllPaged(Pageable pageable){
        Page<EyeExamDTO> result = eyeExamService.findAllEyeExamsPaged(pageable);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EyeExamDTO> findById(@PathVariable Long id){
        EyeExamDTO result = eyeExamService.findEyeExamById(id);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<EyeExamDTO> insert(@RequestBody EyeExamDTO dto){
        dto = eyeExamService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
