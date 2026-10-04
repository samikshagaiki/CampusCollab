package com.campuscollab.dto;

import java.time.LocalDateTime;

import com.campuscollab.entity.ApplicationStatus;

public class ApplicationResponse {

    private Long id;

    private Long opportunityId;
    private String opportunityTitle;

    private Long applicantId;
    private String applicantName;

    private String message;

    private ApplicationStatus status;

    private LocalDateTime createdAt;

    public ApplicationResponse(
            Long id,
            Long opportunityId,
            String opportunityTitle,
            Long applicantId,
            String applicantName,
            String message,
            ApplicationStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getMessage() {
        return message;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}