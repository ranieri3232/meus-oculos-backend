package dev.rlucassmd.meusOculosApi.dto;

import java.io.Serial;
import java.io.Serializable;

public class AppointmentInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long examId;
    private Long patientId;
    private Long eventId;

    public AppointmentInsertDTO() {
    }

    public AppointmentInsertDTO(Long examId, Long patientId, Long eventId) {
        this.examId = examId;
        this.patientId = patientId;
        this.eventId = eventId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
