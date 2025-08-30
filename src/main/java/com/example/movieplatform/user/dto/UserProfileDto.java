package com.example.movieplatform.user.dto;

import com.example.movieplatform.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class UserProfileDto {
    private String name;
    private String createdAt;
    private String currentLoginAt;
    private String role;

    public static UserProfileDto fromEntity(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreatedAt = user.getCreatedAt() != null ? user.getCreatedAt().format(formatter) : null;
        String formattedLoginAt = user.getCurrentLoginAt() != null ? user.getCurrentLoginAt().format(formatter) : null;

        return new UserProfileDto(user.getName(), formattedCreatedAt, formattedLoginAt, user.getRole().getDisplayName());
    }
}
