package com.example.yammarket.repository;

import com.example.yammarket.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserId(String userId);
}

