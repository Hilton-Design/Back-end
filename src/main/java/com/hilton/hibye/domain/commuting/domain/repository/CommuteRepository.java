package com.hilton.hibye.domain.commuting.domain.repository;

import com.hilton.hibye.domain.commuting.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteRepository extends JpaRepository<Commute, Long> {
}
