package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Appointment;
import dev.rlucassmd.meusOculosApi.entities.Event;
import dev.rlucassmd.meusOculosApi.entities.EyeExam;
import dev.rlucassmd.meusOculosApi.entities.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class AppointmentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private PatientDTO patient;

    private EyeExamDTO eyeExam;

    private EventDTO event;

    private Date serviceDate;

    private Date arrivedDate;

    private Date receivedDate;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, PatientDTO patient, EyeExamDTO eyeExam, EventDTO event, Date serviceDate, Date arrivedDate, Date receivedDate) {
        this.id = id;
        this.patient = patient;
        this.eyeExam = eyeExam;
        this.event = event;
        this.serviceDate = serviceDate;
        this.arrivedDate = arrivedDate;
        this.receivedDate = receivedDate;
    }

    public AppointmentDTO(Appointment entity) {
        this(
            entity.getId(),
            new PatientDTO(entity.getPatient()),
            new EyeExamDTO(entity.getEyeExam()),
            new EventDTO(entity.getEvent()),
            entity.getServiceDate(),
            entity.getArrivedDate(),
            entity.getReceivedDate()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public EyeExamDTO getEyeExam() {
        return eyeExam;
    }

    public void setEyeExam(EyeExamDTO eyeExam) {
        this.eyeExam = eyeExam;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }

    public void setArrivedDate(Date arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
}
