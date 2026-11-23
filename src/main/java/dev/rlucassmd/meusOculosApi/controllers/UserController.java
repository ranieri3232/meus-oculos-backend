package dev.rlucassmd.meusOculosApi.controllers;

import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserInsertDTO;
import dev.rlucassmd.meusOculosApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity<Page<UserDTO>> getAllUsersPaged(Pageable pageable){
        Page<UserDTO> page = userService.findAllPaged(pageable);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    ResponseEntity<UserDTO> insertNewUser(@RequestBody UserInsertDTO dto){
        UserDTO newUserDto = userService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserDto  .getId())
                .toUri();
        return ResponseEntity.created(uri).body(newUserDto);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto){
        dto = userService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
