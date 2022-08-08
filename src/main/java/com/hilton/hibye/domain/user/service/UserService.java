package com.hilton.hibye.domain.user.service;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.global.security.oauth.AuthUserDetails;
import com.hilton.hibye.global.security.oauth.AuthUserDetailsService;
import com.hilton.hibye.global.security.oauth.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final KakaoOAuthService kakaoOAuthService;
    private final AuthenticationManager authenticationManager;
    private final AuthUserDetailsService authUserDetailsService;

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
    public Authentication kakaoLogin(String code) {
        User user  = kakaoOAuthService.getUserProfile(kakaoOAuthService.getOAuthToken(code)).toEntity();
        if (!userFacade.isSaved(user)) {
            userRepository.save(user);
        }

        AuthUserDetails authUserDetails = (AuthUserDetails) authUserDetailsService.loadUserByUsername(user.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserDetails, null, authUserDetails.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
