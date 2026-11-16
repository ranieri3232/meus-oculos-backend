package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Patient;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

public class PatientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String nickname;
    private String cpf;
    private String cns;
    private Date birthdate;
    private String profession;
    private String phoneNumber;
    private Integer monthlyIncome;
    private Instant createdAt;
    private Instant updatedAt;

    public PatientDTO() {
    }

    public PatientDTO(Long id, String name, String nickname, String cpf, String cns, Date birthdate, String profession, String phoneNumber, Integer monthlyIncome, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.cpf = cpf;
        this.cns = cns;
        this.birthdate = birthdate;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.monthlyIncome = monthlyIncome;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PatientDTO(Patient entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.cpf = entity.getCpf();
        this.cns = entity.getCns();
        this.birthdate = entity.getBirthdate();
        this.profession = entity.getProfession();
        this.phoneNumber = entity.getPhoneNumber();
        this.monthlyIncome = entity.getMonthlyIncome();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
