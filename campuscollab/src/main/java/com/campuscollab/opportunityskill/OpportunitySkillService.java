package com.campuscollab.opportunityskill;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OpportunitySkillService {

    private final OpportunitySkillRepository repository;

    public OpportunitySkillService(
            OpportunitySkillRepository repository) {

        this.repository = repository;
    }

    public List<OpportunitySkillResponse> getRequiredSkills(
            Long opportunityId) {

        return repository
                .findByOpportunityId(opportunityId)
                .stream()
                .map(opportunitySkill ->
                        new OpportunitySkillResponse(
                                opportunitySkill
                                        .getSkill()
                                        .getId(),
                                opportunitySkill
                                        .getSkill()
                                        .getName()
                        )
                )
                .toList();
    }
}