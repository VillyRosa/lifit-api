package com.lift.api.userGoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/goals")
public class UserGoalController {

    @Autowired
    private UserGoalService userGoalService;

    @GetMapping
    public ResponseEntity<Page<UserGoalResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userGoalService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGoalResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userGoalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserGoalResponseDTO> create(@RequestBody NewUserGoalDTO newUserGoalDTO) {
        return ResponseEntity.ok(userGoalService.create(newUserGoalDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGoalResponseDTO> updateById(@PathVariable UUID id, @RequestBody NewUserGoalDTO newUserGoalDTO) {
        return ResponseEntity.ok(userGoalService.updateById(id, newUserGoalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserGoalResponseDTO> deleteById(@PathVariable UUID id) {
        userGoalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
