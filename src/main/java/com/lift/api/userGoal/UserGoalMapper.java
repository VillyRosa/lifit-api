package com.lift.api.userGoal;

import com.lift.api.user.User;

public class UserGoalMapper {

    public static UserGoalResponseDTO map(UserGoal userGoal) {
        return new UserGoalResponseDTO(
                userGoal.getId(),
                userGoal.getWeight(),
                userGoal.getDeadline(),
                userGoal.getAchieved(),
                userGoal.getCreatedAt(),
                userGoal.getUpdatedAt()
        );
    }

    public static UserGoal map(User user, NewUserGoalDTO newUserGoalDTO) {
        return new UserGoal(
                user,
                newUserGoalDTO.weight(),
                newUserGoalDTO.deadline()
        );
    }

}
