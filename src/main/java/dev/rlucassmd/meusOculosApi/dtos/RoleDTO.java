package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Role;

public record RoleDTO(Long id, String authority) {
    public static RoleDTO fromEntity(Role role){
        return new RoleDTO(
                role.getId(),
                role.getAuthority()
        );
    }
}
