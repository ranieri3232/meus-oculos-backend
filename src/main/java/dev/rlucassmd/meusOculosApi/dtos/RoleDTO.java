package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class RoleDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;
    private String authority;

    public RoleDTO(){
    }

    public RoleDTO(String authority, Long id) {
        this.authority = authority;
        this.id = id;
    }

    public RoleDTO(Role entity){
        this.authority = entity.getAuthority();
        this.id = entity.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(getId(), roleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
