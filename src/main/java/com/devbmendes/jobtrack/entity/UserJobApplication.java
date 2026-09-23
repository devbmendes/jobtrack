package com.devbmendes.jobtrack.entity;

import com.devbmendes.jobtrack.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_job_application")
public class UserJobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    @Column(nullable = false, unique = true, updatable = false)
    private String reference;

    public UserJobApplication() {}
    public UserJobApplication(
            User user,
            JobApplication jobApplication) {

        this.user = user;
        this.jobApplication = jobApplication;
        this.status = Status.APPLIED;
        this.createdAt = LocalDateTime.now();
        this.reference = generateUserJobReference();
    }

    public String getUserJobReference() {
        return reference;
    }

    public void setUserJobReference(String reference) {
        this.reference = reference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    private String generateUserJobReference() {
        return "JTA-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 6)
                        .toUpperCase();
    }

}
