package com.devbmendes.jobtrack.entity;

public class Company {
    private Long id;
    private String name;
    private String websiteUrl;
    private String location;
    private String description;

    public Company(String name, String websiteUrl, String location, String description) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.location = location;
        this.description = description;
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
}
