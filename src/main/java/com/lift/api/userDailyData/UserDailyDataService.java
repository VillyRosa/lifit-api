package com.lift.api.userDailyData;

import com.lift.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public UserDailyDataDTO create(NewUserDailyDataDTO userDailyData) {
        var user = authService.getAuthenticatedUser();
        return UserDailyDataMapper.map(userDailyDataRepository.save(UserDailyDataMapper.map(user, userDailyData)));
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
