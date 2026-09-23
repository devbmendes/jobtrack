package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.exceptions.UserAlreadyAssociatedException;
import com.devbmendes.jobtrack.repository.UserJobApplicationRepository;
import com.devbmendes.jobtrack.repository.UserRepository;

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
    public void saveUserJobApplication(Long userId, Long jobApplicationId) {

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

        userJobApplicationRepository.save(userJobApplication);
    }
}
