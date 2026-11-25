package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Ubs;

public record UbsDTO(Long id, String name, Integer esf, AddressDTO address, ManagerDTO manager) {
    public static UbsDTO fromEntity(Ubs ubs){
        return new UbsDTO(
                ubs.getId(),
                ubs.getName(),
                ubs.getEsf(),
                AddressDTO.fromEntity(ubs.getAddress()),
                ManagerDTO.fromEntity(ubs.getManagedBy())
        );
    }
}
