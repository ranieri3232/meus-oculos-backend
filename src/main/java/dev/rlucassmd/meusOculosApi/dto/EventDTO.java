package dev.rlucassmd.meusOculosApi.dto;

import dev.rlucassmd.meusOculosApi.entities.Event;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class EventDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date eventDate;

    private String location;

    private AddressDTO address;

    private LocalTime eventTime;

    private String description;

    public EventDTO() {
    }

    public EventDTO(Event entity){
        this.id = entity.getId();
        this.eventDate = entity.getEventDate();
        this.address = new AddressDTO(entity.getAddress());
        this.location = entity.getLocation();
        this.eventTime = entity.getEventTime();
        this.description = entity.getDescription();

    }

    public EventDTO(Long id, Date eventDate, String location, AddressDTO address, LocalTime eventTime, String description) {
        this.id = id;
        this.eventDate = eventDate;
        this.location = location;
        this.address = address;
        this.eventTime = eventTime;
        this.description = description;
    }

    public EventDTO(Long id, Date eventDate, String location) {
        this.id = id;
        this.eventDate = eventDate;
        this.location = location;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
