package com.hilton.hibye.domain.commute.domain.repository;

import com.hilton.hibye.domain.commute.domain.Commute;
import com.hilton.hibye.domain.commute.presentation.dto.response.CommuteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CommuteRepository extends JpaRepository<Commute, Long> {

    Optional<Commute> findByNameAndGoToWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByNameAndGoToWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);

    boolean existsByNameAndGetOffWorkTimeBetween(String name, LocalDateTime today, LocalDateTime tomorrow);

    Optional<Page<Commute>> findByGoToWorkTimeAfter(LocalDateTime today, Pageable pageable);

    Optional<Page<Commute>> findByGoToWorkTimeBetween(LocalDateTime yesterday, LocalDateTime today, Pageable pageable);
}
