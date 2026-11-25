package dev.rlucassmd.meusOculosApi.dtos;

import dev.rlucassmd.meusOculosApi.entities.EyeExam;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public record EyeExamDTO(Long id, Date examDate, String lensColor, String lensType, BigDecimal sphereRight, BigDecimal cylindricalRight, Integer axisRight, BigDecimal sphereLeft, BigDecimal cylindricalLeft, Integer axisLeft, BigDecimal additional, String observations, Instant createdAt, boolean isChosen) {
    public static EyeExamDTO fromEntity(EyeExam entity){
        return new EyeExamDTO(
                entity.getId(),
                entity.getExamDate(),
                entity.getLensColor(),
                entity.getLensType(),
                entity.getSphereRight(),
                entity.getCylindricalRight(),
                entity.getAxisRight(),
                entity.getSphereLeft(),
                entity.getCylindricalLeft(),
                entity.getAxisLeft(),
                entity.getAdditional(),
                entity.getObservations(),
                entity.getCreatedAt(),
                entity.isChosen()
            );
    }
}
