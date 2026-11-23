package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.AppointmentDTO;
import dev.rlucassmd.meusOculosApi.dtos.AppointmentInsertDTO;
import dev.rlucassmd.meusOculosApi.dtos.EventDTO;
import dev.rlucassmd.meusOculosApi.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable Long id){
        AppointmentDTO appointmentDTO = appointmentService.findByAppointmentId(id);
        return ResponseEntity.ok().body(appointmentDTO);
    }

    @GetMapping(value = "/patients/{id}")
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByPatientId(@PathVariable Long id){
        List<AppointmentDTO> appointments = appointmentService.listAllAppointmentsByUserId(id);
        return ResponseEntity.ok().body(appointments);
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> insertNewAppointment(@RequestBody AppointmentInsertDTO appointmentInsertDTO){
        AppointmentDTO appointmentDTO = appointmentService.insertNewAppointment(appointmentInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appointmentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(appointmentDTO);
    }
}
