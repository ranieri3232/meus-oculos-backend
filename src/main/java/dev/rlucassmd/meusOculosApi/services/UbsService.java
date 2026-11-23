package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.UbsDTO;
import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.Ubs;
import dev.rlucassmd.meusOculosApi.entities.User;
import dev.rlucassmd.meusOculosApi.repositories.AddressRepository;
import dev.rlucassmd.meusOculosApi.repositories.UbsRepository;
import dev.rlucassmd.meusOculosApi.repositories.UserRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.DatabaseException;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UbsService {
    @Autowired
    private UbsRepository ubsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UbsDTO>findAllPaged(Pageable pageable){
        Page<Ubs> page = ubsRepository.findAll(pageable);

        return page.map(UbsDTO::new);
    }
    @Transactional(readOnly = true)
    public List<UbsDTO>findAll(){
        List<Ubs> list = ubsRepository.findAll();

        return list.stream().map(UbsDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public UbsDTO findById(Long id){
        Optional<Ubs> optional = ubsRepository.findById(id);
        Ubs ubs = optional.orElseThrow(() -> new ResourceNotFoundException("UBS not found!"));
        return new UbsDTO(ubs);
    }

    @Transactional
    public UbsDTO insert(UbsDTO ubsDTO){
        Ubs entity = new Ubs();
        copyDtoToEntity(ubsDTO, entity);
        entity = ubsRepository.save(entity);
        return new UbsDTO(entity);
    }

    @Transactional
    public UbsDTO update(Long id, UbsDTO ubsDTO){
        try{
            Ubs entity = ubsRepository.getReferenceById(id);
            copyDtoToEntity(ubsDTO, entity);
            entity = ubsRepository.saveAndFlush(entity);
            return new UbsDTO(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found with id: " + id);
        }
    }

    public void delete(Long id){
        try{
            if(!ubsRepository.existsById(id)){
                throw new ResourceNotFoundException("Not found a product with id: " + id);
            }
            ubsRepository.deleteById(id);
        }catch(IllegalArgumentException e){
            throw new ResourceNotFoundException("Not found a product with id: " + id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    public void copyDtoToEntity(UbsDTO dto, Ubs entity){
        User manager = userRepository.getReferenceById(dto.getManagedBy().getId());
        entity.setName(dto.getName());
        entity.setEsf(dto.getEsf());
        entity.setManagedBy(manager);
        Address address = new Address();
        if(dto.getAddress() != null){
            address = addressRepository.getReferenceById(dto.getAddress().getId());
            entity.setAddress(address);
        } else {
            address.setDistrict(dto.getAddress().getDistrict());
            address.setNumber(dto.getAddress().getNumber());
            address.setStreet(dto.getAddress().getStreet());
        }
    }
}
