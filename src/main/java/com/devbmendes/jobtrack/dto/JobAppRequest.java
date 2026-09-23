package com.devbmendes.jobtrack.dto;



public class JobAppRequest {

    private Long companyId;
    private String position;
    private String requirements;
    private String location;
    private String notes;

    public JobAppRequest(){}

    public JobAppRequest(Long companyId, String position, String requirements, String location, String notes) {
        this.companyId = companyId;
        this.position = position;
        this.requirements = requirements;
        this.location = location;
        this.notes = notes;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
}
