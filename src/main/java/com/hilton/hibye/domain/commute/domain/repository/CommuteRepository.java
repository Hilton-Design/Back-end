package com.hilton.hibye.domain.commute.domain.repository;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {

    Optional<Commute> findByUserAndGoToWorkTimeBetween(User user, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByUserAndGoToWorkTimeBetween(User user, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByUserAndGetOffWorkTimeBetween(User user, LocalDateTime today, LocalDateTime tomorrow);

    Optional<Page<Commute>> findByGoToWorkTimeAfter(LocalDateTime today, Pageable pageable);

    Optional<Page<Commute>> findByGoToWorkTimeBetween(LocalDateTime yesterday, LocalDateTime today, Pageable pageable);
}
