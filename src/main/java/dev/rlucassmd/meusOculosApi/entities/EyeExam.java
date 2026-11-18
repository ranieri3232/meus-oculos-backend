package dev.rlucassmd.meusOculosApi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_eye_exams")
public class EyeExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date examDate;

    private String lensColor;

    private String lensType;

    @Column( precision = 4, scale = 2)
    private BigDecimal sphereRight;

    @Column( precision = 4, scale = 2)
    private BigDecimal cylindricalRight;

    private Integer axisRight;

    @Column( precision = 4, scale = 2)
    private BigDecimal sphereLeft;

    @Column( precision = 4, scale = 2)
    private BigDecimal cylindricalLeft;


    private Integer axisLeft;

    @Column( precision = 4, scale = 2)
    private BigDecimal additional;

    private String observations;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    private boolean isChosen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public EyeExam() {
    }

    public EyeExam(Long id, Date examDate, String lensColor, String lensType, BigDecimal sphereRight, BigDecimal cylindricalRight, Integer axisRight, BigDecimal sphereLeft, BigDecimal cylindricalLeft, Integer axisLeft, BigDecimal additional, String observations, Patient patient, boolean isChosen) {
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
        this.patient = patient;
        this.isChosen = isChosen;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EyeExam eyeExam = (EyeExam) o;
        return Objects.equals(id, eyeExam.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
