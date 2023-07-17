package com.JWTDemo.JWT.demo.repo;

import com.JWTDemo.JWT.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail (String Email);
}
