package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.User;

import java.util.List;

public record UserInsertDTO(Long id, String name, String email, String password, String cpf, List<RoleDTO> roles, AddressDTO address) {
    public static UserInsertDTO fromEntity(User user){
        List<RoleDTO> roles = user.getRoles().stream().map(RoleDTO::fromEntity).toList();
        return new UserInsertDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCpf(),
                roles,
                AddressDTO.fromEntity(user.getAddress())
        );
    }
}
