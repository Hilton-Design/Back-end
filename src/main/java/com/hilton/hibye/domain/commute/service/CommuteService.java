package com.hilton.hibye.domain.commute.service;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.commute.facade.CommuteFacade;
import com.hilton.hibye.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final UserFacade userFacade;
    private final CommuteFacade commuteFacade;

    @Transactional
    public void goToWork(String name) {
        commuteFacade.validateGoToWork(name);

        // TODO :: findByName -> getCurrentUser
        Commute commute = Commute.createCommute(userFacade.findUserByName(name));
        commute.setRelation();

        commuteRepository.save(commute);
    }

    @Transactional
    public void getOffWork(String name) {

        commuteFacade.validateGetOffWork(name);

        // TODO :: findByName -> getCurrentUser
        Commute commute = commuteFacade.findCommuteByNameAndToday(name);
        commute.setGetOffWorkTime();
    }
}
