package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dto.EyeExamDTO;
import dev.rlucassmd.meusOculosApi.entities.EyeExam;
import dev.rlucassmd.meusOculosApi.entities.Patient;
import dev.rlucassmd.meusOculosApi.repositories.EyeExamRepository;
import dev.rlucassmd.meusOculosApi.repositories.PatientRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EyeExamService {
    @Autowired
    private EyeExamRepository eyeExamRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public Page<EyeExamDTO> findAllEyeExamsPaged(Pageable pageable){
        Page<EyeExam> page = eyeExamRepository.findAll(pageable);
        return page.map(EyeExamDTO::new);
    }

    @Transactional(readOnly = true)
    public EyeExamDTO findEyeExamById(Long id){
        Optional<EyeExam> optional = eyeExamRepository.findById(id);
        optional.orElseThrow(() -> new ResourceNotFoundException("Eye exam not found!"));
        return new EyeExamDTO(optional.get());
    }

    @Transactional
    public EyeExamDTO insert(EyeExamDTO eyeExamDTO){
        EyeExam entity = new EyeExam();
        copyDtoToEntity(eyeExamDTO, entity);
        entity = eyeExamRepository.save(entity);
        return new EyeExamDTO(entity);
    }

    private void copyDtoToEntity(EyeExamDTO dto, EyeExam entity) {
        Patient patient = patientRepository.getReferenceById(dto.getPatient().getId());
        entity.setId(dto.getId());
        entity.setExamDate(dto.getExamDate());
        entity.setLensColor(entity.getLensColor());
        entity.setLensType(entity.getLensType());
        entity.setCylindricalLeft(dto.getCylindricalLeft());
        entity.setAxisLeft(dto.getAxisLeft());
        entity.setSphereLeft(dto.getSphereLeft());

        entity.setCylindricalRight(dto.getCylindricalRight());
        entity.setAxisRight(dto.getAxisRight());
        entity.setSphereRight(dto.getSphereRight());

        entity.setAdditional(dto.getAdditional());
        entity.setObservations(dto.getObservations());

        entity.setChosen(dto.isChosen());
        entity.setPatient(patient);

    }
}
