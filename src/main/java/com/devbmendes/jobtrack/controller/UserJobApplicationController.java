package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.dto.UpdateStatusRequest;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.repository.UserJobApplicationRepository;
import com.devbmendes.jobtrack.service.UserJobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateStatus(@Valid @RequestBody
            UpdateStatusRequest updateStatusRequest) {

        userJobApplicationService.updateStatusJob(
                updateStatusRequest
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Application status updated successfully",
                        null
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        userJobApplicationService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("UserJob deleted",
                null));
    }
}
