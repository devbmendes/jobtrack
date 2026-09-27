package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findByPosition(String position);

}
