package dev.rlucassmd.meusOculosApi.repositories;

import dev.rlucassmd.meusOculosApi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
