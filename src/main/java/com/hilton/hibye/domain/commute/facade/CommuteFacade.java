package com.hilton.hibye.domain.commute.facade;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.commute.exception.*;
import com.hilton.hibye.domain.commute.presentation.dto.response.CommuteResponseDto;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import com.hilton.hibye.domain.user.exception.UserNotFoundException;
import com.hilton.hibye.global.Utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
