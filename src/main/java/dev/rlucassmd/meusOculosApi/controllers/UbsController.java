package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.services.UbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ubs")
public class UbsController {

    @Autowired
    UbsService ubsService;

    @GetMapping
    ResponseEntity<Page<UbsDTO>> getAllUbs(Pageable pageable){
        Page<UbsDTO> page = ubsService.findAllPaged(pageable);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<UbsDTO> getUbsById(@PathVariable Long id){
        UbsDTO ubs = ubsService.findById(id);
        return ResponseEntity.ok().body(ubs);
    }

    @PostMapping
    ResponseEntity<UbsDTO> insertNewUbs(@RequestBody UbsDTO ubsDTO){
        ubsDTO = ubsService.insert(ubsDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ubsDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(ubsDTO);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<UbsDTO> updateUbs(@PathVariable Long id, @RequestBody UbsDTO ubsDTO){
        ubsDTO = ubsService.update(id, ubsDTO);
        return ResponseEntity.ok().body(ubsDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ubsService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
