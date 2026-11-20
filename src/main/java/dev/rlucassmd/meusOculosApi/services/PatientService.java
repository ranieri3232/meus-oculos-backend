package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.PatientDTO;
import dev.rlucassmd.meusOculosApi.entities.Patient;
import dev.rlucassmd.meusOculosApi.repositories.PatientRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.DatabaseException;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public Page<PatientDTO> findAllPatientsPaged(Pageable pageable){
        Page<Patient> page = patientRepository.findAll(pageable);
        return page.map(PatientDTO::new);
    }

    @Transactional(readOnly = true)
    public List<PatientDTO> findAllPatients(){
        List<Patient> list = patientRepository.findAll();
        return list.stream().map(PatientDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PatientDTO findById(Long id){
        Optional<Patient> optional = patientRepository.findById(id);
        Patient entity = optional.orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        return new PatientDTO(entity, entity.getEyeExams());
    }

    @Transactional(readOnly = true)
    public Page<PatientDTO> findBySearchTerm(String term, Pageable pageable){
//        if (term == null || term.trim().isEmpty()) {
//            List<Patient> list = patientRepository.findAll();
//            return list.stream().map(PatientDTO::new).toList();
//        }

        String originalTerm = term.trim();
        String onlyNumbers = originalTerm.replaceAll("\\D", "");

        if (onlyNumbers.length() == 11 && originalTerm.matches("[0-9.-]+")) {
            Page<Patient> page = patientRepository.findByCpf(onlyNumbers, pageable);
            return page.map(item -> new PatientDTO(item, item.getEyeExams()));
            //return patientRepository.findByCpf(onlyNumbers, pageable);
        }

        if (onlyNumbers.length() == 15 && originalTerm.matches("[0-9\\s]+")) {
            Page<Patient> page = patientRepository.findByCns(onlyNumbers, pageable);
            return page.map(item -> new PatientDTO(item, item.getEyeExams()));
        }

        Page<Patient> page = patientRepository.findByNameContainingIgnoreCase(originalTerm, pageable);
        return page.map(item -> new PatientDTO(item, item.getEyeExams()));
    }

    @Transactional(readOnly = true)
    public Page<PatientDTO> searchByNamePaged(String name, Pageable pageable){
        Page<Patient> page = patientRepository.findByNameContainingIgnoreCase(name, pageable);

        return page.map(item -> new PatientDTO(item, item.getEyeExams()));
    }

    @Transactional
    public PatientDTO insert(PatientDTO dto){
        Patient entity = new Patient();
        copyDtoToEntity(dto, entity);
        entity = patientRepository.saveAndFlush(entity);

        return new PatientDTO(entity);
    }

    @Transactional
    public PatientDTO update(Long id, PatientDTO dto){
        try {
            Patient entity = patientRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = patientRepository.saveAndFlush(entity);

            return new PatientDTO(entity, entity.getEyeExams());
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Patient not found!");
        }
    }

    public void delete(Long id) {
        try {
            if(!patientRepository.existsById(id)){
                throw new ResourceNotFoundException("Patient not found!");
            }
            patientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation!");
        }
    }

    private void copyDtoToEntity(PatientDTO dto, Patient entity) {
        entity.setName(dto.getName());
        entity.setNickname(dto.getNickname());
        entity.setCpf(dto.getCpf());
        entity.setCns(dto.getCns());
        entity.setBirthdate(dto.getBirthdate());
        entity.setProfession(dto.getProfession());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setMonthlyIncome(dto.getMonthlyIncome());
    }
}
