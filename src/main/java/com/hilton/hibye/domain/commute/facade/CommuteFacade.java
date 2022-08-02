package com.hilton.hibye.domain.commute.facade;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.commute.exception.AlreadyGetOffWorkException;
import com.hilton.hibye.domain.commute.exception.AlreadyGoToWorkException;
import com.hilton.hibye.domain.commute.exception.GoToWorkYetException;
import com.hilton.hibye.domain.commute.exception.NobodyCommuteException;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.UserNotFoundException;
import com.hilton.hibye.domain.user.facade.UserFacade;
import com.hilton.hibye.global.Utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommuteFacade {

    private final CommuteRepository commuteRepository;
    private final UserRepository userRepository;

    public Commute findCommuteByNameAndToday(String name) {
        return commuteRepository.findByNameAndGoToWorkTimeBetween(name, DateUtil.getToday(), DateUtil.getTomorrow())
                .orElseThrow(() -> NobodyCommuteException.EXCEPTION);
    }

    public void validateGoToWork(String name) {
        if (commuteRepository.existsByNameAndGoToWorkTimeBetween(name, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw AlreadyGoToWorkException.EXCEPTION;
        }
    }

    public void validateGetOffWork(String name) {
        if (!userRepository.existsByName(name)) {
            throw UserNotFoundException.EXCEPTION;
        }
        if (!commuteRepository.existsByNameAndGoToWorkTimeBetween(name, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw GoToWorkYetException.EXCEPTION;
        }
        if (commuteRepository.existsByNameAndGetOffWorkTimeBetween(name, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw AlreadyGetOffWorkException.EXCEPTION;
        }
    }
}
