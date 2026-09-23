package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    boolean existsByIdAndUsers_Id(Long jobApplicationId, Long userId);
}
