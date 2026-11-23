package dev.rlucassmd.meusOculosApi.repositories;

import dev.rlucassmd.meusOculosApi.entities.Ubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbsRepository extends JpaRepository<Ubs, Long> {
}
