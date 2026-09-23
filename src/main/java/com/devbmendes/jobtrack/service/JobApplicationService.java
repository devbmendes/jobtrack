package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.JobAppRequest;
import com.devbmendes.jobtrack.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {

    public JobApplication saveJobApplication(JobAppRequest userJobAppRequest);

    public List<JobApplication> findAll();

}
