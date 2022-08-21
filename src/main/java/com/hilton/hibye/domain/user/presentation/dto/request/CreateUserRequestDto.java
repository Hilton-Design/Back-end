package com.hilton.hibye.domain.user.presentation.dto.request;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.type.Authority;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
public class CreateUserRequestDto {

    private String email;
    private String password;
    private String name;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }
}
