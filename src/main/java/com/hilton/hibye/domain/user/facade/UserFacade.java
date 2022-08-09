package com.hilton.hibye.domain.user.facade;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.UserAlreadyExistsException;
import com.hilton.hibye.domain.user.exception.UserNotFoundException;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.global.security.oauth.AuthUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void validateSignUp(CreateUserRequestDto request) {
        if (
                userRepository.existsByEmail(request.getEmail()) ||
                userRepository.existsByName(request.getName())
        ) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        AuthUserDetails authUserDetails= (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authUserDetails.getUser();
    }

    public boolean isSaved(User user) {
        if (
                userRepository.existsByEmail(user.getEmail()) ||
                userRepository.existsByName(user.getName())
        ) {
            return true;
        }
        return false;
    }
}
