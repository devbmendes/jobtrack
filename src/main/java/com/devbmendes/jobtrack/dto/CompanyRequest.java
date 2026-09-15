package com.devbmendes.jobtrack.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {
    @NotBlank
    private String name;
    private String websiteUrl;
    @NotBlank
    private String location;
    private String description;

    public CompanyRequest(String name, String websiteUrl, String location, String description) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.description = description;
    }
    public CompanyRequest(){}

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
}
