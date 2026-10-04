package com.campuscollab.opportunityskill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunitySkillRepository
        extends JpaRepository<OpportunitySkill, Long> {

    List<OpportunitySkill>
    findByOpportunityId(Long opportunityId);
}