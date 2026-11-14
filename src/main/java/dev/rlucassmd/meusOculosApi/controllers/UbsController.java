package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.services.UbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ubs")
public class UbsController {

    @Autowired
    UbsService ubsService;

    @GetMapping
    ResponseEntity<Page<UbsDTO>> getAllUbs(Pageable pageable){
        Page<UbsDTO> page = ubsService.findAll(pageable);
        System.out.println();
        return ResponseEntity.ok().body(page);
    }

}
