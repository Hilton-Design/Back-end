package com.hilton.hibye.domain.user.domain.repository;


import com.hilton.hibye.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String username);
}
