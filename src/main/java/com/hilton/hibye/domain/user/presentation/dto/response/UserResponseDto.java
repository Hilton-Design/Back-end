package com.hilton.hibye.domain.user.presentation.dto.response;

import com.hilton.hibye.domain.user.domain.User;
import lombok.Builder;


@Builder
public class UserResponseDto {

    private String name;

    private String email;

    private String password;

    private String thumbnailImage;

    private String profileImage;

    private int lateCount;

    private int commuteCount;

    private int hourlyWage;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .lateCount(user.getLateCount())
                .commuteCount(user.getCommutingList().size())
                .hourlyWage(user.getHourlyWage())
                .build();
    }
}
