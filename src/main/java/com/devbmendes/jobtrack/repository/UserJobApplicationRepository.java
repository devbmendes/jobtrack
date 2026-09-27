package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJobApplicationRepository extends JpaRepository<UserJobApplication,Long> {
    boolean existsByUserIdAndJobApplicationId(Long userId, Long jobApplicationId);
    List<UserJobApplication> findByUserId(Long userId);
    List<UserJobApplication> findByJobApplicationId(Long jobApplicationId);
    Optional<UserJobApplication> findByReference(String reference);
    List<UserJobApplication> findByStatus(Status status);
    List<UserJobApplication> findByUserIdAndStatus(Long userId, Status status);
}
