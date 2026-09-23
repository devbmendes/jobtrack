package com.devbmendes.jobtrack.dto;

public class UserJobAppResponse {
    private String userJobReference;
    private Long jobApplicationId;
    private String status;
    private String createdAt;

    public UserJobAppResponse(){}

    public UserJobAppResponse(String userJobReference, Long jobApplicationId, String status, String createdAt) {
        this.userJobReference = userJobReference;
        this.jobApplicationId = jobApplicationId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getUserJobReference() {
        return userJobReference;
    }

    public void setUserJobReference(String userJobReference) {
        this.userJobReference = userJobReference;
    }

    public Long getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(Long jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
