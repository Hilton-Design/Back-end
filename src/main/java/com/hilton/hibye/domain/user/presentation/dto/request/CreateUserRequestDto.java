package com.hilton.hibye.domain.user.presentation.dto.request;

import com.hilton.hibye.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequestDto {

    private String email;
    private String password;
    private int age;
    private String name;
    private String phone;
    private String address;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .age(age)
                .name(name)
                .phone(phone)
                .address(address)
                .build();
    }

}
