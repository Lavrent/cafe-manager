package com.cafe.manager.repository;

import com.cafe.manager.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUuid(String uuid);

    Optional<UserEntity> findByUsername(String username);
}