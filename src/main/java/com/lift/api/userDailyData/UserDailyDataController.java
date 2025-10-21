package com.lift.api.userDailyData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/daily")
public class UserDailyDataController {

    @Autowired
    private UserDailyDataService userDailyDataService;

    @GetMapping
    public ResponseEntity<Page<UserDailyDataDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userDailyDataService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDailyDataDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userDailyDataService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDailyDataDTO> findById(@RequestBody NewUserDailyDataDTO userDailyData) {
        return ResponseEntity.ok(userDailyDataService.create(userDailyData));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDailyDataDTO> findById(@PathVariable UUID id, @RequestBody NewUserDailyDataDTO userDailyData) {
        return ResponseEntity.ok(userDailyDataService.update(id, userDailyData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        userDailyDataService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
