package com.campuscollab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscollab.entity.CollaborationApplication;

public interface CollaborationApplicationRepository
        extends JpaRepository<CollaborationApplication, Long> {

    Optional<CollaborationApplication>
    findByOpportunityIdAndApplicantId(
            Long opportunityId,
            Long applicantId
    );

    List<CollaborationApplication>
    findByApplicantId(Long applicantId);

    List<CollaborationApplication>
    findByOpportunityId(Long opportunityId);
}