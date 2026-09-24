package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.UserJobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJobApplicationRepository extends JpaRepository<UserJobApplication,Long> {
    boolean existsByUserIdAndJobApplicationId(Long userId, Long jobApplicationId);
    List<UserJobApplication> findByUserId(Long userId);
    List<UserJobApplication> findByJobApplicationId(Long jobApplicationId);
}
