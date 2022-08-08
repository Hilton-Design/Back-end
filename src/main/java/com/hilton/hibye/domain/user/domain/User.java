package com.hilton.hibye.domain.user.domain;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.user.domain.type.Role;
import com.hilton.hibye.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    private String thumbnailImage;

    private String profileImage;

    private int lateCount;

    private int commuteCount;

    private int hourlyWage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commute> commutingList = new ArrayList<>();

    @Builder
    public User (String name, String email, String thumbnailImage, String profileImage, Role role, int commuteCount, int hourlyWage) {
        this.name = name;
        this.email = email;
        this.thumbnailImage = thumbnailImage;
        this.profileImage = profileImage;
        this.role = role;
        this.commuteCount = commuteCount;
        this.hourlyWage = hourlyWage;
    }

    public void late() {
        this.lateCount++;
    }

    public void commute() { this.commuteCount++; }
}
