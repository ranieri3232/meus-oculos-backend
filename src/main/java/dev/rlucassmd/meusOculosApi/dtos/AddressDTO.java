package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Address;

public record AddressDTO (Long id, String street, String number, String district){

    public static AddressDTO fromEntity(Address address){
        if(address == null){return null;}
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getDistrict()
        );
    }
}
