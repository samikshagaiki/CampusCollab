package com.campuscollab.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "collaboration_applications",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"opportunity_id", "applicant_id"}
        )
    }
)
public class CollaborationApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "opportunity_id", nullable = false)
    private CollaborationOpportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public CollaborationApplication() {
    }

    public Long getId() {
        return id;
    }

    public CollaborationOpportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(CollaborationOpportunity opportunity) {
        this.opportunity = opportunity;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public void setId(Long id) {
		this.id = id;
	}
}