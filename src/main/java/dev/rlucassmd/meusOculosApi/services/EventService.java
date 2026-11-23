package dev.rlucassmd.meusOculosApi.services;

import dev.rlucassmd.meusOculosApi.dtos.EventDTO;
import dev.rlucassmd.meusOculosApi.dtos.EyeExamDTO;
import dev.rlucassmd.meusOculosApi.entities.Address;
import dev.rlucassmd.meusOculosApi.entities.Event;
import dev.rlucassmd.meusOculosApi.entities.EyeExam;
import dev.rlucassmd.meusOculosApi.repositories.AddressRepository;
import dev.rlucassmd.meusOculosApi.repositories.EventRepository;
import dev.rlucassmd.meusOculosApi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllEventsPaged(Pageable pageable){
        Page<Event> page = eventRepository.findAll(pageable);

        return page.map(EventDTO::new);
    }

    @Transactional(readOnly = true)
    public EventDTO findEventById(Long id){
        Optional<Event> optional = eventRepository.findById(id);
        optional.orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        return new EventDTO(optional.get());
    }

    @Transactional
    public EventDTO insert(EventDTO dto){
        Event entity = new Event();
        copyDtoToEntity(dto, entity);
        entity = eventRepository.save(entity);
        return new EventDTO(entity);
    }

    @Transactional
    public EventDTO updateEvent(Long id, EventDTO dto){
        try {
            Event event = eventRepository.getReferenceById(id);
            copyDtoToEntity(dto, event);
            event = eventRepository.saveAndFlush(event);
            return new EventDTO(event);
        }catch (Exception e){
            throw new ResourceNotFoundException("Event not found");
        }
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {

        entity.setDescription(dto.getDescription());
        entity.setEventDate(dto.getEventDate());
        entity.setEventTime(dto.getEventTime());
        entity.setLocation(dto.getLocation());
        Address address = new Address();
        if(dto.getAddress().getId() != null){
            address = addressRepository.getReferenceById(dto.getAddress().getId());
            entity.setAddress(address);
        } else {
            address.setDistrict(dto.getAddress().getDistrict());
            address.setNumber(dto.getAddress().getNumber());
            address.setStreet(dto.getAddress().getStreet());
        }
        entity.setAddress(address);

    }


}
