package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.UserJobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJobApplicationRepository extends JpaRepository<UserJobApplication,Long> {
    boolean existsByUserIdAndJobApplicationId(Long userId, Long jobApplicationId);
    List<UserJobApplication> findByUserId(Long userId);
    List<UserJobApplication> findByJobApplicationId(Long jobApplicationId);
}
