package com.hilton.hibye.domain.commute.service;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.commute.facade.CommuteFacade;
import com.hilton.hibye.domain.commute.presentation.dto.response.CommuteResponseDto;
import com.hilton.hibye.domain.user.domain.User;
import com.hilton.hibye.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final UserFacade userFacade;
    private final CommuteFacade commuteFacade;

    @Transactional
    public void goToWork() {
        User user = userFacade.getCurrentUser();

//        commuteFacade.validateGoToWork(user.getName());
        user.commute();

        // TODO :: findByName -> getCurrentUser
        Commute commute = Commute.createCommute(user);
        commute.setRelation();

        commuteRepository.save(commute);
    }

    @Transactional
    public void getOffWork() {
        User user = userFacade.getCurrentUser();

//        commuteFacade.validateGetOffWork(user.getName());

        // TODO :: findByName -> getCurrentUser
        Commute commute = commuteFacade.findCommuteByNameAndToday(user.getName());
        commute.setGetOffWorkTime();
    }

    @Transactional(readOnly = true)
    public Page<CommuteResponseDto> getCommuteList(Pageable pageable) {
        return commuteFacade.getCommuteList(pageable).map(CommuteResponseDto::of);
    }

    @Transactional(readOnly = true)
    public Page<CommuteResponseDto> getTodayList(Pageable pageable) {
        return commuteFacade.getTodayList(pageable).map(CommuteResponseDto::of);
    }

    @Transactional(readOnly = true)
    public Page<CommuteResponseDto> getYesterdayList(Pageable pageable) {
        return commuteFacade.getYesterdayList(pageable).map(CommuteResponseDto::of);
    }
}
