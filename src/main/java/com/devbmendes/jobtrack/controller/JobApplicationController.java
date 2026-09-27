package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;


import com.devbmendes.jobtrack.dto.JobAppRequest;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-app")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse<JobApplication>> save(@RequestBody JobAppRequest request){
        JobApplication jobApplication = jobApplicationService.saveJobApplication(request);
        return ResponseEntity.ok(new ApiResponse<>("JobApplication created",jobApplication));
    }
}
