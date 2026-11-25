package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dto.AppointmentDTO;
import dev.rlucassmd.meusOculosApi.dto.AppointmentInsertDTO;
import dev.rlucassmd.meusOculosApi.entities.*;
import dev.rlucassmd.meusOculosApi.repositories.AppointmentRepository;
import dev.rlucassmd.meusOculosApi.repositories.EventRepository;
import dev.rlucassmd.meusOculosApi.repositories.EyeExamRepository;
import dev.rlucassmd.meusOculosApi.repositories.PatientRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EyeExamRepository eyeExamRepository;;

    @Autowired
    private EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<AppointmentDTO> listAllAppointmentsByUserId(Long id){
        List<Appointment> list = appointmentRepository.findByPatientId(id);
        return list.stream().map(x -> new AppointmentDTO(x)).toList();
    }
    @Transactional(readOnly = true)
    public AppointmentDTO findByAppointmentId(Long id){
        Optional<Appointment> optional = appointmentRepository.findById(id);
        Appointment appointment = optional.orElseThrow(() -> new ResourceNotFoundException("Appointment not found!"));
        return new AppointmentDTO(appointment);
    }
    @Transactional()
    public AppointmentDTO insertNewAppointment(AppointmentInsertDTO dto){
        Appointment entity = new Appointment();
        copyAppointmentInsertDtoToEntity(dto, entity);
        entity = appointmentRepository.save(entity);
        return new AppointmentDTO(entity);
    }

    private void copyAppointmentInsertDtoToEntity(AppointmentInsertDTO dto, Appointment entity) {
        Optional<Patient> patientOptional = patientRepository.findById(dto.getPatientId());
        Optional<Event> eventOptional = eventRepository.findById(dto.getEventId());
        Optional<EyeExam> eyeExamOptional = eyeExamRepository.findById(dto.getExamId());

        Patient patient = patientOptional.orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        Event event = eventOptional.orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        EyeExam eyeExam = eyeExamOptional.orElseThrow(() -> new ResourceNotFoundException("Eye exam not found!"));

        entity.setPatient(patient);
        entity.setEvent(event);
        entity.setEyeExam(eyeExam);
    }




}
