package com.hilton.hibye.domain.user.presentation.dto.request;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequestDto {

    private String email;
    private String password;
    private String name;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .role(Role.USER)
                .build();
    }

}
