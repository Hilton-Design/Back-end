package com.hilton.hibye.domain.user.facade;

import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.UserAlreadyExistsException;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public void validateSignUp(CreateUserRequestDto request) {
        if (
                userRepository.existsByEmail(request.getEmail()) ||
                userRepository.existsByPhone(request.getPhone()) ||
                userRepository.existsByName(request.getName())
        ) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }
}
