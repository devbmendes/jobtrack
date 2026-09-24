package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.repository.UserJobApplicationRepository;
import com.devbmendes.jobtrack.service.UserJobApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-applications")
public class UserJobApplicationController {
    private final UserJobApplicationService userJobApplicationService;

    public UserJobApplicationController(UserJobApplicationService userJobApplicationService) {
        this.userJobApplicationService = userJobApplicationService;
    }

    @PostMapping("/{jobApplicationId}/apply/{userId}")
    public ResponseEntity<ApiResponse<UserJobAppResponse>> apply(
            @PathVariable Long jobApplicationId,
            @PathVariable Long userId) {

        UserJobAppResponse response =
                userJobApplicationService
                        .saveUserJobApplication(userId, jobApplicationId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "Application submitted successfully",
                        response
                ));
    }
}
