package com.lift.api.userGoal;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UserGoalResponseDTO(
        UUID id,
        Double weight,
        LocalDate deadline,
        Boolean achieved,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
