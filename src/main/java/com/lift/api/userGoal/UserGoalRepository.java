package com.lift.api.userGoal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGoalRepository extends JpaRepository<UserGoal, UUID> {
    Page<UserGoal> findAllByUserId(UUID user_id, Pageable pageable);
}
