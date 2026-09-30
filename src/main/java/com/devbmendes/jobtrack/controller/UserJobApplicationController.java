package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.dto.UpdateStatusRequest;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.service.UserJobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-job-applications")
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
    public ResponseEntity<ApiResponse<Void>> updateStatus(@RequestParam String reference) {

        userJobApplicationService.updateStatus(reference);
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
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<UserJobApplication>>> findByUserId(@PathVariable Long id){
        List<UserJobApplication> userJobApplicationList = userJobApplicationService.findByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "UserJobApplication for this user",userJobApplicationList));
    }
    @GetMapping("/by-status")
    public List<UserJobApplication> findByStatus(@RequestParam Long userId, @RequestParam String status) {
         return userJobApplicationService.findByUserIdAndStatus(
                userId,
                status
        );

    }
}
