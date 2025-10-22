package com.lift.api.userGoal;

import com.lift.api.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserGoalService {

    @Autowired
    private UserGoalRepository userGoalRepository;

    @Autowired
    private AuthService authService;

    public Page<UserGoalResponseDTO> findAll(Pageable pageable) {
        var user = authService.getAuthenticatedUser();
        return userGoalRepository.findAllByUserId(user.getId(), pageable).map(UserGoalMapper::map);
    }

    public UserGoalResponseDTO findById(UUID id) {
        var user = authService.getAuthenticatedUser();

        return userGoalRepository.findById(id)
                .filter(data -> data.getUser().getId().equals(user.getId()))
                .map(UserGoalMapper::map)
                .orElse(null);
    }

    public UserGoalResponseDTO create(NewUserGoalDTO newUserGoalDTO) {
        var user = authService.getAuthenticatedUser();
        return UserGoalMapper.map(userGoalRepository.save(UserGoalMapper.map(user, newUserGoalDTO)));
    }

    public UserGoalResponseDTO updateById(UUID id, NewUserGoalDTO newUserGoalDTO) {
        var user = authService.getAuthenticatedUser();
        var userGoalInDB = userGoalRepository.findById(id).orElse(null);
        if (userGoalInDB != null && userGoalInDB.getUser() == user) {
            var userGoalToUpdate = UserGoalMapper.map(user, newUserGoalDTO);
            userGoalToUpdate.setId(id);
            return UserGoalMapper.map(userGoalRepository.save(userGoalToUpdate));
        }

        return null;
    }

    public void deleteById(UUID id) {
        var user = authService.getAuthenticatedUser();
        var userGoal = userGoalRepository.findById(id).orElse(null);
        if (userGoal != null && userGoal.getUser() == user) {
            userGoalRepository.deleteById(id);
        }
    }

}
