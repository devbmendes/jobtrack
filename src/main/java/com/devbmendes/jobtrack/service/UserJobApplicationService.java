package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UpdateStatusRequest;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.enums.Status;


import java.util.List;

public interface UserJobApplicationService {
    public UserJobAppResponse saveUserJobApplication(Long userId, Long jobApplicationId);
    public List<UserJobApplication> getAll();
    public UserJobAppResponse findByReference(String reference);
    public void updateStatusJob(UpdateStatusRequest updateStatusRequest);
    public void deleteById(Long userJobApplication);
    List<UserJobApplication> findByUserIdAndStatus(Long userId, String status);

}
