package com.devbmendes.jobtrack.entity;

import com.devbmendes.jobtrack.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "jobApplication")
    private Set<UserJobApplication> userJobApplications = new HashSet<>();
    private String position;
    private String requirements;
    private String location;
    private String notes;
    private LocalDateTime createdAt;

    public JobApplication(Company company, String position,
                          String requirements,String location, String notes) {
        this.company = company;
        this.position = position;
        this.requirements = requirements;
        this.location = location;
        this.notes = notes;
        this.createdAt = LocalDateTime.now();
    }
    public JobApplication(){}

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
    public Set<UserJobApplication> getUserJobApplications() {
        return userJobApplications;
    }
}
