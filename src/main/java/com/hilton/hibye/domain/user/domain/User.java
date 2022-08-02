package com.hilton.hibye.domain.user.domain;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.user.domain.type.Role;
import com.hilton.hibye.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_table")
@Getter
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

    private int commuteCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commute> commutingList = new ArrayList<>();

    @Builder
    public User (String name, String email, String password, int age, String phone, String address, Role role, int commuteCount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.commuteCount = commuteCount;
    }

    public void late() {
        this.lateCount += 1;
    }
}
