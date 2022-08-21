package com.hilton.hibye.domain.user.service;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.LoginUserRequestDto;
import com.hilton.hibye.domain.user.presentation.dto.response.TokenResponseDto;
import com.hilton.hibye.global.redis.RedisService;
import com.hilton.hibye.global.security.jwt.JwtProperties;
import com.hilton.hibye.global.security.jwt.JwtTokenProvider;
import com.hilton.hibye.global.security.jwt.JwtValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserFacade userFacade;
    private final JwtValidateService jwtValidateService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public TokenResponseDto loginUser(LoginUserRequestDto request) {
        User user = userFacade.findUserByEmail(request.getEmail());
        userFacade.checkUserPassword(user, request.getPassword());

        final String accessToken = jwtTokenProvider.createAccessToken(request.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken(request.getEmail());
        redisService.setDataExpire(request.getEmail(), refreshToken, JwtProperties.REFRESH_TOKEN_VALID_TIME);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void logoutUser() {
        User user = userFacade.getCurrentUser();
        redisService.deleteData(user.getEmail());
    }

    public TokenResponseDto getNewAccessToken(String refreshToken) {
        jwtValidateService.validateRefreshToken(refreshToken);

        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(
                        jwtValidateService.getEmail(refreshToken)
                ))
                .build();
    }
}
