package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UpdateStatusRequest;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.entity.UserJobApplication;


import java.util.List;

public interface UserJobApplicationService {
    public UserJobAppResponse saveUserJobApplication(Long userId, Long jobApplicationId);
    public List<UserJobApplication> getAll();
    public UserJobApplication findByReference(String reference);
    public void updateStatusJob(UpdateStatusRequest updateStatusRequest);
    public void deleteById(Long userJobApplication);
}
