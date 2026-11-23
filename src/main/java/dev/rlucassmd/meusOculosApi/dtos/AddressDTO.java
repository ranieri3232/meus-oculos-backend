package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Address;

import java.io.Serial;
import java.io.Serializable;

public class AddressDTO implements Serializable {

    @Serial
    private static final long  serialVersionUID = 1L;

    private Long id;
    private String street;
    private String number;
    private String district;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.district = address.getDistrict();
    }

    public AddressDTO() {
    }

    public AddressDTO(Long id, String street, String number, String district) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
