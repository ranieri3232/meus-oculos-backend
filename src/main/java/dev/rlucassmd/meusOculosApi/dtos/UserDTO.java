package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.User;

import java.util.List;

public record UserDTO(Long id, String name, String email, String cpf, List<RoleDTO> roles, AddressDTO address) {

    public static UserDTO fromEntity(User user){
        List<RoleDTO> rolesDTO =
                user.getRoles().stream().map(RoleDTO::fromEntity).toList();
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                rolesDTO,
                AddressDTO.fromEntity(user.getAddress())
        );
    }
}
