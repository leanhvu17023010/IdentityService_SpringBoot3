package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Spring JPA tự động generate 1 câu query kiểm tra sự tồn tại của field username với value = parameter truyền vào
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
