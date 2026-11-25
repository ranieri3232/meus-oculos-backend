package dev.rlucassmd.meusOculosApi.dto;

import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;

public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String cpf;

    private Address address;

    private HashSet<RoleDTO> roles = new HashSet<>();

    public UserDTO(Long id, String name, String email, String cpf, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.address = address;
    }

    public UserDTO() {
    }

    public UserDTO(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.address = entity.getAddress();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HashSet<RoleDTO> getRoles() {
        return roles;
    }
}
