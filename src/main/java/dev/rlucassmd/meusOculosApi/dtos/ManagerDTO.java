package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.User;

public record ManagerDTO (Long id, String name){
    public static ManagerDTO fromEntity(User user){
        if(user == null){ return null; }
        return new ManagerDTO(
                user.getId(),
                user.getName()
        );
    }
}
