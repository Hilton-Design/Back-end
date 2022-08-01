package com.hilton.hibye.domain.user.domain;

import com.hilton.hibye.domain.user.domain.type.Role;
import com.hilton.hibye.global.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private int age;

    private String phone;

    private String address;

    private int lateCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User (String name, String email, String password, int age, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public void late() {
        this.lateCount += 1;
    }
}
