package com.hilton.hibye.domain.commute.domain.repository;

import com.hilton.hibye.domain.commute.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {

    Optional<Commute> findByNameAndGoToWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByNameAndGoToWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByNameAndGetOffWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);
}
