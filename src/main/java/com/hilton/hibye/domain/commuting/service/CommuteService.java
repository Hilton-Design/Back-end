package com.hilton.hibye.domain.commuting.service;

import com.hilton.hibye.domain.commuting.domain.Commute;
import com.hilton.hibye.domain.commuting.domain.repository.CommuteRepository;
import com.hilton.hibye.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommuteService {

    private final CommuteRepository commuteRepository;
    private final UserRepository userRepository;

    @Transactional
    public void goToWork(String name) {
        // TODO :: findByName -> getCurrentUser
        Commute commute = Commute.createCommute(userRepository.findByName(name));
        commute.setRelation();
        commuteRepository.save(commute);
    }
}
