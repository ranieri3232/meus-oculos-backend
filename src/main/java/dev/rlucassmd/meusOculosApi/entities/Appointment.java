package dev.rlucassmd.meusOculosApi.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_appointments")
public class Appointment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eye_exam_id", nullable = false)
    private EyeExam eyeExam;


    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private Date serviceDate;

    private Date arrivedDate;

    private Date receivedDate;

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, EyeExam eyeExam, Event event, Date serviceDate, Date arrivedDate, Date receivedDate) {
        this.id = id;
        this.patient = patient;
        this.eyeExam = eyeExam;
        this.event = event;
        this.serviceDate = serviceDate;
        this.arrivedDate = arrivedDate;
        this.receivedDate = receivedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public EyeExam getEyeExam() {
        return eyeExam;
    }

    public void setEyeExam(EyeExam eyeExam) {
        this.eyeExam = eyeExam;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
