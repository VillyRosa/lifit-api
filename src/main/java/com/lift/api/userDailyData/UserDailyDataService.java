package com.lift.api.userDailyData;

import com.lift.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class UserDailyDataService {

    @Autowired
    private UserDailyDataRepository userDailyDataRepository;

    @Autowired
    private AuthService authService;

    public Page<UserDailyDataDTO> findAll(Pageable pageable) {
        var user = authService.getAuthenticatedUser();
        return userDailyDataRepository.findByUserId(user.getId(), pageable).map(UserDailyDataMapper::map);
    }

    public UserDailyDataDTO findById(UUID id) {
        var user = authService.getAuthenticatedUser();

        return userDailyDataRepository.findById(id)
                .filter(data -> data.getUser().getId().equals(user.getId()))
                .map(UserDailyDataMapper::map)
                .orElse(null);
    }

    public UserDailyDataDTO create(NewUserDailyDataDTO newUserDailyDataDTO) {
        var user = authService.getAuthenticatedUser();

        boolean exists = userDailyDataRepository.existsByUserIdAndCreatedAtBetween(
                user.getId(),
                LocalDate.now()
                        .atStartOfDay()
                        .atOffset(ZoneOffset.of("-03:00")),
                LocalDate.now()
                        .plusDays(1)
                        .atStartOfDay()
                        .atOffset(ZoneOffset.of("-03:00"))
        );

        if (exists) {
            return null;
        }

        var data = new UserDailyData(user, newUserDailyDataDTO.weight(), newUserDailyDataDTO.height());
        var saved = userDailyDataRepository.save(data);

        return UserDailyDataMapper.map(saved);
    }

    public UserDailyDataDTO update(UUID id, NewUserDailyDataDTO newUserDailyDataDTO) {
        var user = authService.getAuthenticatedUser();
        var userDailyDataInDB = userDailyDataRepository.findById(id).orElse(null);
        if (userDailyDataInDB != null && userDailyDataInDB.getUser() == user) {
            var userDailyDataToUpdate = UserDailyDataMapper.map(user, newUserDailyDataDTO);
            userDailyDataToUpdate.setId(id);
            return UserDailyDataMapper.map(userDailyDataRepository.save(userDailyDataToUpdate));
        }

        return null;
    }

    public void deleteById(UUID id) {
        var user = authService.getAuthenticatedUser();
        var userDailyData = userDailyDataRepository.findById(id).orElse(null);
        if (userDailyData != null && userDailyData.getUser() == user) {
            userDailyDataRepository.deleteById(id);
        }
    }

}
