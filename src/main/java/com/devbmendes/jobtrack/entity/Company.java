package com.devbmendes.jobtrack.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String websiteUrl;
    private String location;
    private String description;
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "company")
    private List<JobApplication> jobApplications = new ArrayList<>();

    public Company(String name, String websiteUrl, String location, String description) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.description = description;
        this.createdAt = LocalDateTime.now();

    }
    public Company(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
