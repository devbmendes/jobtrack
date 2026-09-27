package com.devbmendes.jobtrack.repository;


import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    long countByRole(Role role);
}
