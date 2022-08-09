package com.hilton.hibye.domain.commute.facade;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.commute.exception.*;
import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.UserNotFoundException;
import com.hilton.hibye.global.Utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommuteFacade {

    private final CommuteRepository commuteRepository;
    private final UserRepository userRepository;

    public Commute findCommuteByUserAndToday(User user) {
        return commuteRepository.findByUserAndGoToWorkTimeBetween(user, DateUtil.getToday(), DateUtil.getTomorrow())
                .orElseThrow(() -> NobodyCommuteException.EXCEPTION);
    }

    public void validateGoToWork(User user) {
        if (commuteRepository.existsByUserAndGoToWorkTimeBetween(user, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw AlreadyGoToWorkException.EXCEPTION;
        }
    }

    public void validateGetOffWork(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw UserNotFoundException.EXCEPTION;
        }
        if (!commuteRepository.existsByUserAndGoToWorkTimeBetween(user, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw GoToWorkYetException.EXCEPTION;
        }
        if (commuteRepository.existsByUserAndGetOffWorkTimeBetween(user, DateUtil.getToday(), DateUtil.getTomorrow())) {
            throw AlreadyGetOffWorkException.EXCEPTION;
        }
    }

    public Page<Commute> getCommuteList(Pageable pageable) {
        return commuteRepository.findAll(pageable);

    }

    public Page<Commute> getTodayList(Pageable pageable) {
        return commuteRepository.findByGoToWorkTimeAfter(DateUtil.getToday(), pageable)
                .orElseThrow(() -> CommuteNotFoundException.EXCEPTION);
    }

    public Page<Commute> getYesterdayList(Pageable pageable) {
        return commuteRepository.findByGoToWorkTimeBetween(DateUtil.getYesterday(), DateUtil.getToday(), pageable)
                .orElseThrow(() -> CommuteNotFoundException.EXCEPTION);
    }
}
