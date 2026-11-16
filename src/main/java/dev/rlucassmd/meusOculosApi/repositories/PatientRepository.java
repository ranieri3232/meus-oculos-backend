package dev.rlucassmd.meusOculosApi.repositories;

import dev.rlucassmd.meusOculosApi.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByCpf(String cpf);
    Optional<Patient> findByCns(String cns);

    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
