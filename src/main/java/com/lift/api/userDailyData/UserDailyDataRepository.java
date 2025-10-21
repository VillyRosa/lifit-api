package com.lift.api.userDailyData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface UserDailyDataRepository extends JpaRepository<UserDailyData, UUID> {
    Page<UserDailyData> findByUserId(UUID user_id, Pageable pageable);
    boolean existsByUserIdAndCreatedAtBetween(UUID userId, OffsetDateTime startOfDay, OffsetDateTime endOfDay);
}
