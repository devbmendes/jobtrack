package com.devbmendes.jobtrack.dto;

import java.time.LocalDateTime;

public class CompanyResponse {
    private String name;
    private String websiteUrl;
    private String location;
    private String description;
    private LocalDateTime createdAt;

    public CompanyResponse(){

    }
    public CompanyResponse(String name, String websiteUrl, String location, String description, LocalDateTime createdAt) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.description = description;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
