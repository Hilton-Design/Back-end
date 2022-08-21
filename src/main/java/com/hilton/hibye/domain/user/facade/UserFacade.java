package com.hilton.hibye.domain.user.facade;

import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.PasswordMismatchException;
import com.hilton.hibye.domain.user.exception.UserAlreadyExistsException;
import com.hilton.hibye.domain.user.exception.UserNotFoundException;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import com.hilton.hibye.global.security.oauth.AuthDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public boolean isSaved(User user) {
        if (
                userRepository.existsByEmail(user.getEmail()) ||
                userRepository.existsByName(user.getName())
        ) {
            return true;
        }
        return false;
    }

    public void checkUserPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
    }

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
