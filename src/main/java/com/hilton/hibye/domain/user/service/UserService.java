package com.hilton.hibye.domain.user.service;

import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.domain.user.presentation.dto.request.CreateUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public void signUp(CreateUserRequestDto request) {

        userFacade.validateSignUp(request);

        userRepository.save(request.toEntity());
    }
}
