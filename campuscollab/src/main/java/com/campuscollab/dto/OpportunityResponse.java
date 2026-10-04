package com.campuscollab.dto;

import java.time.LocalDateTime;

import com.campuscollab.entity.OpportunityStatus;

public class OpportunityResponse {

    private Long id;
    private String title;
    private String description;
    private Integer teamSize;

    private Long ownerId;
    private String ownerName;

    private OpportunityStatus status;
    private LocalDateTime createdAt;

    public OpportunityResponse(
            Long id,
            String title,
            String description,
            Integer teamSize,
            Long ownerId,
            String ownerName,
            OpportunityStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.teamSize = teamSize;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public OpportunityStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}