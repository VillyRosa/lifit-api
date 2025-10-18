package com.lift.api.user;

public record RegisterDTO(
        String nickname,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
