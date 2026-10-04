package com.campuscollab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.entity.OpportunityStatus;

public interface CollaborationOpportunityRepository
        extends JpaRepository<CollaborationOpportunity, Long> {

    List<CollaborationOpportunity> findByStatus(
            OpportunityStatus status);

    List<CollaborationOpportunity> findByOwnerId(Long ownerId);
}