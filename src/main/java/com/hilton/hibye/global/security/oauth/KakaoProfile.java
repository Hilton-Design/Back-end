package com.hilton.hibye.global.security.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfile {

    private Long id;
    private LocalDateTime connected_at;
    private Properties properties;
    private String email;

    @Getter
    class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

}
