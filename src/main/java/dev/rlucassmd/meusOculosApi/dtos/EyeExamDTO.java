package dev.rlucassmd.meusOculosApi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.rlucassmd.meusOculosApi.entities.EyeExam;
import dev.rlucassmd.meusOculosApi.entities.Patient;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class EyeExamDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date examDate;
    private String lensColor;
    private String lensType;
    private BigDecimal sphereRight;
    private BigDecimal cylindricalRight;
    private Integer axisRight;
    private BigDecimal sphereLeft;
    private BigDecimal cylindricalLeft;
    private Integer axisLeft;
    private BigDecimal additional;
    private String observations;
    private Instant createdAt;
    private boolean isChosen;

    private Patient patient;

    public EyeExamDTO(Long id, Date examDate, String lensColor, String lensType, BigDecimal sphereRight, BigDecimal cylindricalRight, Integer axisRight, BigDecimal sphereLeft, BigDecimal cylindricalLeft, Integer axisLeft, BigDecimal additional, String observations, Instant createdAt, boolean isChosen, Patient patient) {
        this.id = id;
        this.examDate = examDate;
        this.lensColor = lensColor;
        this.lensType = lensType;
        this.sphereRight = sphereRight;
        this.cylindricalRight = cylindricalRight;
        this.axisRight = axisRight;
        this.sphereLeft = sphereLeft;
        this.cylindricalLeft = cylindricalLeft;
        this.axisLeft = axisLeft;
        this.additional = additional;
        this.observations = observations;
        this.createdAt = createdAt;
        this.isChosen = isChosen;
        this.patient = patient;
    }

    public EyeExamDTO(EyeExam entity) {
        this.id = entity.getId();
        this.examDate = entity.getExamDate();
        this.lensColor = entity.getLensColor();
        this.lensType = entity.getLensType();
        this.sphereRight = entity.getSphereRight();
        this.cylindricalRight = entity.getCylindricalRight();
        this.axisRight = entity.getAxisRight();
        this.sphereLeft = entity.getSphereLeft();
        this.cylindricalLeft = entity.getCylindricalLeft();
        this.axisLeft = entity.getAxisLeft();
        this.additional = entity.getAdditional();
        this.observations = entity.getObservations();
        this.createdAt = entity.getCreatedAt();
        this.isChosen = entity.isChosen();
        this.patient = entity.getPatient();
    }

    public EyeExamDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getLensColor() {
        return lensColor;
    }

    public void setLensColor(String lensColor) {
        this.lensColor = lensColor;
    }

    public String getLensType() {
        return lensType;
    }

    public void setLensType(String lensType) {
        this.lensType = lensType;
    }

    public BigDecimal getSphereRight() {
        return sphereRight;
    }

    public void setSphereRight(BigDecimal sphereRight) {
        this.sphereRight = sphereRight;
    }

    public BigDecimal getCylindricalRight() {
        return cylindricalRight;
    }

    public void setCylindricalRight(BigDecimal cylindricalRight) {
        this.cylindricalRight = cylindricalRight;
    }

    public Integer getAxisRight() {
        return axisRight;
    }

    public void setAxisRight(Integer axisRight) {
        this.axisRight = axisRight;
    }

    public BigDecimal getSphereLeft() {
        return sphereLeft;
    }

    public void setSphereLeft(BigDecimal sphereLeft) {
        this.sphereLeft = sphereLeft;
    }

    public BigDecimal getCylindricalLeft() {
        return cylindricalLeft;
    }

    public void setCylindricalLeft(BigDecimal cylindricalLeft) {
        this.cylindricalLeft = cylindricalLeft;
    }

    public Integer getAxisLeft() {
        return axisLeft;
    }

    public void setAxisLeft(Integer axisLeft) {
        this.axisLeft = axisLeft;
    }

    public BigDecimal getAdditional() {
        return additional;
    }

    public void setAdditional(BigDecimal additional) {
        this.additional = additional;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
