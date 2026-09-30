package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.UpdateStatusRequest;
import com.devbmendes.jobtrack.dto.UserJobAppResponse;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.entity.User;
import com.devbmendes.jobtrack.entity.UserJobApplication;
import com.devbmendes.jobtrack.enums.Status;
import com.devbmendes.jobtrack.exceptions.InvalidStatusException;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.exceptions.UserAlreadyAssociatedException;
import com.devbmendes.jobtrack.repository.UserJobApplicationRepository;
import com.devbmendes.jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJobApplicationImpl implements UserJobApplicationService {

    private final UserRepository userRepository;
    private final JobApplicationServiceImpl jobApplicationService;
    private final UserJobApplicationRepository userJobApplicationRepository;
    private final UserService userService;

    public UserJobApplicationImpl(UserRepository userRepository,
                                  JobApplicationServiceImpl  jobApplicationService, UserJobApplicationRepository userJobApplicationRepository, UserService userService) {
        this.userRepository = userRepository;
        this.jobApplicationService = jobApplicationService;
        this.userJobApplicationRepository = userJobApplicationRepository;
        this.userService = userService;
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

    @Override
    public List<UserJobApplication> getAll() {
        return userJobApplicationRepository.findAll();
    }

    @Override
    public UserJobAppResponse findByReference(String reference) {
        Optional<UserJobApplication> userJobApplication = userJobApplicationRepository.findByReference(reference);
               if (userJobApplication.isEmpty()){
                   throw new ResourceNotFoundException("UserJobApplication with this REFERENCE : "
                   +reference+" not found");
               }
               UserJobAppResponse userJobAppResponse = new UserJobAppResponse();
               userJobAppResponse.setJobApplicationId(userJobApplication.get().getId());
               userJobAppResponse.setUserJobReference(userJobApplication.get().getUserJobReference());
               userJobAppResponse.setStatus(userJobApplication.get().getStatus().toString());
               userJobAppResponse.setCreatedAt(userJobApplication.get().getCreatedAt().toString());

               return userJobAppResponse;
    }

    private UserJobApplication isReferenceValid(String reference){
        Optional<UserJobApplication> userJobApplication = userJobApplicationRepository
                .findByReference(reference.toUpperCase());
        if (userJobApplication.isEmpty()){
            throw new ResourceNotFoundException("UserJobApplication with this REFERENCE : "
                    +reference +" not found");
        }
        return userJobApplication.get();
    }
    @Override
    public void updateStatusJob(UpdateStatusRequest request) {

        Optional<UserJobApplication> userJobApplication = userJobApplicationRepository
                .findByReference(request.getReference().toUpperCase());
        if (userJobApplication.isEmpty()){
            throw new ResourceNotFoundException("UserJobApplication with this REFERENCE : "
                    +request.getReference() +" not found");
        }
        Status status;

        try {
            status = Status.valueOf(
                    request.getStatus().toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException(
                    "Invalid status: " + request.getStatus()
            );
        }

        userJobApplication.get().setStatus(status);
        userJobApplicationRepository.save(userJobApplication.get());
    }

    @Override
    public void deleteById(Long userJobApplication) {
        Optional<UserJobApplication> userFound = userJobApplicationRepository.findById(userJobApplication);
        if (userFound.isEmpty()){
            throw new ResourceNotFoundException("JobApplication with this ID :"+userJobApplication
            +" not found");
        }
        userJobApplicationRepository.deleteById(userJobApplication);

    }

    @Override
    public List<UserJobApplication> findByUserIdAndStatus(Long userId, String statusRequest) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with ID: " + userId + " not found"
                ));
        Status status;

        try {
            status = Status.valueOf(
                    statusRequest.toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException(
                    "Invalid status: " + statusRequest
            );
        }
        return userJobApplicationRepository.findByUserIdAndStatus(userId,status);

    }
    @Override
    public void updateStatus(String reference){
        UserJobApplication userJobApplication = isReferenceValid(reference.toUpperCase());

        if (userJobApplication.getStatus().toString().equals("APPLIED")){
            userJobApplication.setStatus(Status.SCREENING);
            userJobApplicationRepository.save(userJobApplication);
        } else if (userJobApplication.getStatus().toString().equals("SCREENING")) {
            userJobApplication.setStatus(Status.INTERVIEW);
            userJobApplicationRepository.save(userJobApplication);
        } else if (userJobApplication.getStatus().toString().equals("INTERVIEW")) {
            userJobApplication.setStatus(Status.OFFER);
            userJobApplicationRepository.save(userJobApplication);
        } else if (userJobApplication.getStatus().toString().equals("REJECTED")) {
            throw new InvalidStatusException("UserJobApplication with this reference" +
                    " : "+reference+" is already REJECTED");

        }
    }
    @Override
    public int rejectUserJob(String reference){
        UserJobApplication userJobApplication = isReferenceValid(reference);
        userJobApplication.setStatus(Status.REJECTED);
        userJobApplicationRepository.save(userJobApplication);
        return 1;
    }

    @Override
    public List<UserJobApplication> findByUserId(Long id) {
        userService.findById(id);
        return userJobApplicationRepository.findByUserId(id);
    }
}
