package com.lift.api.userDailyData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserDailyDataDTO(UUID id, Double height, Double weight, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
