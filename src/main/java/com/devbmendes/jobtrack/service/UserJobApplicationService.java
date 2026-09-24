package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UserJobAppResponse;

public interface UserJobApplicationService {
    public UserJobAppResponse saveUserJobApplication(Long userId, Long companyId);
}
