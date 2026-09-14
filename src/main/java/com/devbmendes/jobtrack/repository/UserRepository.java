package com.devbmendes.jobtrack.repository;


import com.devbmendes.jobtrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
