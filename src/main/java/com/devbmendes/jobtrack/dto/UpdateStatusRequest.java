package com.devbmendes.jobtrack.dto;

public class UpdateStatusRequest {

    private String status;
    private String reference;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
