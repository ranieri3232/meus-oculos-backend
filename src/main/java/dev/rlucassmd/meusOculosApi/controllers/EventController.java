package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dto.EventDTO;
import dev.rlucassmd.meusOculosApi.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAllEventsPaged(Pageable pageable){
        Page<EventDTO> page = eventService.findAllEventsPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> findEventById(@PathVariable Long id){
        EventDTO event = eventService.findEventById(id);
        return ResponseEntity.ok().body(event);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody EventDTO dto){
        dto = eventService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<EventDTO> updatePatient(@PathVariable Long id, @RequestBody EventDTO dto){
        dto = eventService.updateEvent(id, dto);
        return ResponseEntity.ok().body(dto);
    }



}
