package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.Ubs;

import java.io.Serial;
import java.io.Serializable;

public class UbsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer esf;
    private Address address;

    public UbsDTO(Long id, String name, Integer esf) {
        this.id = id;
        this.name = name;
        this.esf = esf;
    }

    public UbsDTO(Ubs entity){
        this(entity.getId(), entity.getName(), entity.getEsf());
        setAddress(entity.getAddress());
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

    public Integer getEsf() {
        return esf;
    }

    public void setEsf(Integer esf) {
        this.esf = esf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
