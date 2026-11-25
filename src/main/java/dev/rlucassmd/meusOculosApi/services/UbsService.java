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

        return page.map(UbsDTO::fromEntity);
    }
    @Transactional(readOnly = true)
    public List<UbsDTO>findAll(){
        List<Ubs> list = ubsRepository.findAll();

        return list.stream().map(UbsDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public UbsDTO findById(Long id){
        Optional<Ubs> optional = ubsRepository.findById(id);
        Ubs ubs = optional.orElseThrow(() -> new ResourceNotFoundException("UBS not found!"));
        return UbsDTO.fromEntity(ubs);
    }

    @Transactional
    public UbsDTO insert(UbsDTO ubsDTO){
        Ubs entity = new Ubs();
        copyDtoToEntity(ubsDTO, entity);
        entity = ubsRepository.save(entity);
        return UbsDTO.fromEntity(entity);
    }

    @Transactional
    public UbsDTO update(Long id, UbsDTO ubsDTO){
        try{
            Ubs entity = ubsRepository.getReferenceById(id);
            copyDtoToEntity(ubsDTO, entity);
            entity = ubsRepository.saveAndFlush(entity);
            return UbsDTO.fromEntity(entity);
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

    public void deleteUbsAddress(Long id){
        Ubs ubs = ubsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ubs not found!"));
        ubs.setAddress(null);
        ubsRepository.save(ubs);
    }

    public void copyDtoToEntity(UbsDTO dto, Ubs entity){

        entity.setName(dto.name());
        entity.setEsf(dto.esf());
        if(dto.manager() != null) {
            User manager = userRepository.getReferenceById(dto.manager().id());
            entity.setManagedBy(manager);
        }
        if(dto.address() != null) {
            Address address = new Address();
            address.setDistrict(dto.address().district());
            address.setNumber(dto.address().number());
            address.setStreet(dto.address().street());
            entity.setAddress(address);
        }
    }
}
