package com.hilton.hibye.domain.commute.domain;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.global.Utils.DateUtil;
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

    @Column(name = "get_to_work_time", nullable = false)
    private LocalDateTime goToWorkTime;

    @Column(name = "get_off_work_time")
    private LocalDateTime getOffWorkTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Commute(User user, LocalDateTime goToWorkTime, LocalDateTime getOffWorkTime) {
        this.user = user;
        this.goToWorkTime = goToWorkTime;
        this.getOffWorkTime = getOffWorkTime;
    }

    public static Commute createCommute(User user) {
        return Commute.builder()
                .user(user)
                .goToWorkTime(DateUtil.getCommuteTime())
                .getOffWorkTime(null)
                .build();
    }

    public void setRelation() {
        this.user.getCommutingList().add(this);
    }

    public void setGetOffWorkTime() {
        this.getOffWorkTime = DateUtil.getCommuteTime();
    }
}
