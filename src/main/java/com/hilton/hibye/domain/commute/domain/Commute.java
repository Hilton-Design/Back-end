package com.hilton.hibye.domain.commute.domain;

import com.hilton.hibye.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "commute_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Commute {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "late_count", nullable = false)
    private int lateCount;

    @Column(name = "get_to_work_time", nullable = false)
    private LocalDateTime goToWorkTime;

    @Column(name = "get_off_work_time")
    private LocalDateTime getOffWorkTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Commute(String name, String email, int lateCount, User user, LocalDateTime goToWorkTime, LocalDateTime getOffWorkTime) {
        this.name = name;
        this.email = email;
        this.lateCount = lateCount;
        this.user = user;
        this.goToWorkTime = goToWorkTime;
        this.getOffWorkTime = getOffWorkTime;
    }

    public static Commute createCommute(User user) {
        return Commute.builder()
                .email(user.getEmail())
                .name(user.getName())
                .user(user)
                .lateCount(user.getLateCount())
                .goToWorkTime(LocalDateTime.now().withSecond(0).withNano(0))
                .getOffWorkTime(null)
                .build();
    }

    public void setRelation() {
        this.user.getCommutingList().add(this);
    }

    public void setGetOffWorkTime() {
        this.getOffWorkTime = LocalDateTime.now().withSecond(0).withNano(0);
    }
}
