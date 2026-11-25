package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.Patient;

import java.time.Instant;
import java.util.Date;

public record PatientDTO(Long id, String name, String nickname, String cpf, String cns, Date birthdate, String profession, String phoneNumber, Integer monthlyIncome, Instant createdAt, Instant updatedAt) {
    public static PatientDTO fromEntity(Patient entity){
        return new PatientDTO(
                entity.getId(),
                entity.getName(),
                entity.getNickname(),
                entity.getCpf(),
                entity.getCns(),
                entity.getBirthdate(),
                entity.getProfession(),
                entity.getPhoneNumber(),
                entity.getMonthlyIncome(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
