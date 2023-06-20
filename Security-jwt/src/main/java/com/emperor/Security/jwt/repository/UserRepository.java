package com.emperor.Security.jwt.repository;

import com.emperor.Security.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsernameOrEmail(String username, String email);
}
