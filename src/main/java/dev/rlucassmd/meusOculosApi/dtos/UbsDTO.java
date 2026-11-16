package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.Ubs;
import dev.rlucassmd.meusOculosApi.entities.User;

import java.io.Serial;
import java.io.Serializable;

public class UbsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer esf;
    private Address address;
    private ManagerDTO managedBy;

    public UbsDTO(Long id, String name, Integer esf) {
        this.id = id;
        this.name = name;
        this.esf = esf;
    }

    public UbsDTO(){

    }

    public ManagerDTO getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(ManagerDTO managedBy) {
        this.managedBy = managedBy;
    }

    public UbsDTO(Ubs entity){
        this(entity.getId(), entity.getName(), entity.getEsf());
        setAddress(entity.getAddress());
        if(entity.getManagedBy() != null) {
            setManagedBy(new ManagerDTO(entity.getManagedBy()));
        }
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
