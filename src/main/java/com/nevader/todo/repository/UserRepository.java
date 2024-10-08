package com.nevader.todo.repository;

import com.nevader.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional <User> findByUsername(String username);
    Optional <User> findByEmailOrUsername(String email, String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
