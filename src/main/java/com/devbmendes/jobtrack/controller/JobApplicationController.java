package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;


import com.devbmendes.jobtrack.dto.JobAppRequest;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.service.JobApplicationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<ApiResponse<List<JobApplication>>> findAll(){
        List<JobApplication> list = jobApplicationService.findAll();
        return ResponseEntity.ok(new ApiResponse<>("All JobApplications",list));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobApplication>> findById(@PathVariable Long id){
        JobApplication jobApplication = jobApplicationService.findById(id);
        return ResponseEntity.ok( new ApiResponse<>("JobApplication found",jobApplication));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        jobApplicationService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("JobApplication deleted",null));
    }
}
