package com.example.treeleaf.assignment.user.dao;

import com.example.treeleaf.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);

    Boolean existsByUsername(String name);
}
