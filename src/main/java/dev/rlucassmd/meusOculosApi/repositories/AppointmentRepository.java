package dev.rlucassmd.meusOculosApi.repositories;

import dev.rlucassmd.meusOculosApi.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long id);
    List<Appointment> findByPatientIdAndServiceDateIsNotNull(Long id);
    List<Appointment> findByPatientIdAndServiceDateIsNull(Long id);
    List<Appointment> findByEventId(Long id);
    List<Appointment> findByEventIdAndServiceDateIsNotNull(Long id);
    List<Appointment> findByEventIdAndServiceDateIsNull(Long id);


}
