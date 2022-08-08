package com.hilton.hibye.global.security.oauth;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.type.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class KakaoProfile{
    private Long id;
    private Date connected_at;
    private KakaoAccount kakao_account;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown=true)
    static class KakaoAccount{
        private Profile profile;
        private String email;

        @Getter
        @JsonIgnoreProperties(ignoreUnknown=true)
        static class Profile{
            private String nickname;
            private String thumbnail_image_url;
            private String profile_image_url;
        }
    }

    public User toEntity() {
        return User.builder()
                .name(this.kakao_account.profile.nickname)
                .email(this.kakao_account.email)
                .thumbnailImage(this.kakao_account.profile.thumbnail_image_url)
                .profileImage(this.kakao_account.profile.profile_image_url)
                .role(Role.USER)
                .hourlyWage(0)
                .commuteCount(0)
                .hourlyWage(0)
                .build();
    }
}




