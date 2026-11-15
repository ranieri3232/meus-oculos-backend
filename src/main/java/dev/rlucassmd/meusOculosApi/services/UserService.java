package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.RoleDTO;
import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserInsertDTO;
import dev.rlucassmd.meusOculosApi.entities.Role;
import dev.rlucassmd.meusOculosApi.entities.Ubs;
import dev.rlucassmd.meusOculosApi.entities.User;
import dev.rlucassmd.meusOculosApi.repositories.AddressRepository;
import dev.rlucassmd.meusOculosApi.repositories.RoleRepository;
import dev.rlucassmd.meusOculosApi.repositories.UbsRepository;
import dev.rlucassmd.meusOculosApi.repositories.UserRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.DatabaseException;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO>findAllPaged(Pageable pageable){
        Page<User> list = userRepository.findAll(pageable);

        return list.map(UserDTO::new);
    }
    @Transactional(readOnly = true)
    public List<UserDTO>findAll(){
        List<User> list = userRepository.findAll();

        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> optional = userRepository.findById(id);
        User user = optional.orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO userDTO){
        User entity = new User();
        copyDtoToEntity(userDTO, entity);
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = new Role(2L, "OPERATOR");
        entity.getRoles().add(role);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO ubsDTO){
        try{
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(ubsDTO, entity);
            entity = userRepository.saveAndFlush(entity);
            return new UserDTO(entity);

        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    public void delete(Long id){
        try{
            if(!userRepository.existsById(id)){
                throw new ResourceNotFoundException("User not found!");
            }
            userRepository.deleteById(id);
        }catch(IllegalArgumentException e){
            throw new ResourceNotFoundException("User not found");
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDTO userDto, User entity) {
        entity.setEmail(userDto.getEmail());
        entity.setName(userDto.getName());
        entity.setAddress(userDto.getAddress());
        entity.setCpf(userDto.getCpf());

    }
}
