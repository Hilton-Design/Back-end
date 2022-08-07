package com.hilton.hibye.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilton.hibye.domain.user.domain.OAuthToken;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.global.security.oauth.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final KakaoOAuthService kakaoOAuthService;

    @Value("${content-type}")
    private static String CONTENT_TYPE;

    @Value("${token-request.uri}")
    private String TOKEN_REQUEST_URI;
    @Value("${token-request.body.grant-type}")
    private String GRANT_TYPE;
    @Value("${token-request.body.client-id}")
    private String CLIENT_ID;
    @Value("${token-request.body.request-uri}")
    private String REDIRECT_URI;

    @Value("${profile-request.uri}")
    private String PROFILE_REQUEST_URI;


    @Transactional
    public void signUp(CreateUserRequestDto request) {

        userFacade.validateSignUp(request);

        userRepository.save(request.toEntity());
    }

    @Transactional
    public String kakaoLogin(String code) {
        return kakaoOAuthService.getUserProfile(kakaoOAuthService.getOAuthToken(code));
    }
}
