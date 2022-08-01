package com.hilton.hibye.domain.user.domain.repository;


import com.hilton.hibye.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByName(String name);
}
