package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.AddressDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserDTO;
import dev.rlucassmd.meusOculosApi.dtos.UserInsertDTO;
import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.Role;
import dev.rlucassmd.meusOculosApi.entities.User;
import dev.rlucassmd.meusOculosApi.repositories.AddressRepository;
import dev.rlucassmd.meusOculosApi.repositories.RoleRepository;
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

        return list.map(UserDTO::fromEntity);
    }
    @Transactional(readOnly = true)
    public List<UserDTO>findAll(){
        List<User> list = userRepository.findAll();

        return list.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> optional = userRepository.findById(id);
        User user = optional.orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return UserDTO.fromEntity(user);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO userDTO){
        User entity = new User();
        copyInsertDtoToEntity(userDTO, entity);
//        entity = UserDTO.fromEntity()
        entity.setPassword(passwordEncoder.encode(userDTO.password()));
        Role role = new Role(2L, "OPERATOR");
        entity.getRoles().add(role);
        entity = userRepository.save(entity);
        return UserDTO.fromEntity(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO){
        try{
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(userDTO, entity);
            entity = userRepository.saveAndFlush(entity);
            return UserDTO.fromEntity(entity);

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

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setEmail(dto.email());
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        Address address = new Address();
        if(dto.address().id() != null){
            address = addressRepository.getReferenceById(dto.address().id());
            entity.setAddress(address);
        }else{
            address.setStreet(dto.address().street());
            address.setNumber(dto.address().number());
            address.setDistrict(dto.address().district());
            entity.setAddress(address);
        }

    }

    private void copyInsertDtoToEntity(UserInsertDTO dto, User entity) {
        entity.setEmail(dto.email());
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());

        if(dto.address() != null){
            Address address = new Address();
            address.setDistrict(dto.address().district());
            address.setNumber(dto.address().number());
            address.setStreet(dto.address().street());

            entity.setAddress(address);
        }

    }


}
