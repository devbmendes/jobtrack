package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.exceptions.UserAlreadyAssociatedException;
import com.devbmendes.jobtrack.repository.UserJobApplicationRepository;
import com.devbmendes.jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserJobApplicationImpl implements UserJobApplicationService {

    private final UserRepository userRepository;
    private final JobApplicationServiceImpl jobApplicationService;
    private final UserJobApplicationRepository userJobApplicationRepository;

    public UserJobApplicationImpl(UserRepository userRepository,
                                  JobApplicationServiceImpl  jobApplicationService, UserJobApplicationRepository userJobApplicationRepository) {
        this.userRepository = userRepository;
        this.jobApplicationService = jobApplicationService;
        this.userJobApplicationRepository = userJobApplicationRepository;
    }

    @Override
    public UserJobAppResponse saveUserJobApplication(Long userId, Long jobApplicationId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User with this ID: "
                                        + userId
                                        + " not found"
                        ));

        JobApplication jobApplication =
                jobApplicationService.findById(jobApplicationId);

        boolean alreadyAssociated =
                userJobApplicationRepository
                        .existsByUserIdAndJobApplicationId(
                                userId,
                                jobApplicationId
                        );

        if (alreadyAssociated) {
            throw new UserAlreadyAssociatedException(
                    "User is already associated with this job application"
            );
        }

        UserJobApplication userJobApplication =
                new UserJobApplication(user, jobApplication);

        UserJobApplication userJobApplicationSaved = userJobApplicationRepository.save(userJobApplication);
        return new UserJobAppResponse(
                userJobApplicationSaved.getUserJobReference(),
                userJobApplicationSaved.getId(),
                userJobApplicationSaved.getStatus().toString(),
                userJobApplicationSaved.getCreatedAt().toString());
    }
}
