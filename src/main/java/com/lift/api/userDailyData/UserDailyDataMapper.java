package com.lift.api.userDailyData;

import com.lift.api.user.User;

public class UserDailyDataMapper {

    public static UserDailyDataDTO map(UserDailyData userDailyData) {
        return new UserDailyDataDTO(
                userDailyData.getId(),
                userDailyData.getHeight(),
                userDailyData.getWeight(),
                userDailyData.getCreatedAt(),
                userDailyData.getUpdatedAt()
        );
    }

    public static UserDailyData map(User user, NewUserDailyDataDTO newUserDailyDataDTO) {
        return new UserDailyData(
                user,
                newUserDailyDataDTO.height(),
                newUserDailyDataDTO.weight()
        );
    }

}
