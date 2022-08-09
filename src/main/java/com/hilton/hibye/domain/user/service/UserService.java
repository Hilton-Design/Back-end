package com.hilton.hibye.domain.user.service;

import com.hilton.hibye.domain.user.domain.OAuthToken;
import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.global.security.oauth.AuthUserDetails;
import com.hilton.hibye.global.security.oauth.KakaoOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final KakaoOAuthService kakaoOAuthService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Value("${cos.cosKey}")
    private String cosKey;

    @Transactional
    public void signUp(CreateUserRequestDto request) {

        userFacade.validateSignUp(request);

        userRepository.save(request.toEntity());
    }

    @Transactional
    public void kakaoLogin(String code) {
        OAuthToken oAuthToken = kakaoOAuthService.getOAuthToken(code);
        User user  = kakaoOAuthService.getUserProfile(oAuthToken).toEntity(passwordEncoder, cosKey);

        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), cosKey));
        log.info("authentication: {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
        log.info("user: {}", authUserDetails.getUser());
    }
}
