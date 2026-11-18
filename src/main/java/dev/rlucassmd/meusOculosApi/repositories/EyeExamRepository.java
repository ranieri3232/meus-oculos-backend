package dev.rlucassmd.meusOculosApi.repositories;

import dev.rlucassmd.meusOculosApi.entities.EyeExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EyeExamRepository extends JpaRepository<EyeExam, Long> {

}
