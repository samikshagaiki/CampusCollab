package com.campuscollab.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository
        extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByOpportunityId(Long opportunityId);

    Optional<TeamMember> findByOpportunityIdAndUserId(
            Long opportunityId,
            Long userId
    );

    long countByOpportunityId(Long opportunityId);
}