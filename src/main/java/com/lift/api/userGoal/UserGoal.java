package com.lift.api.userGoal;

import com.lift.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_goals")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double weight;

    private LocalDate deadline;

    @Column(nullable = false)
    private Boolean achieved = false;

    @Column(updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public UserGoal(User user, Double weight, LocalDate deadline) {
        this.user = user;
        this.weight = weight;
        this.deadline = deadline;
    }
}
