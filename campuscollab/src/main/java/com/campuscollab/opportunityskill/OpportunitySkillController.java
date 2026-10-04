package com.campuscollab.opportunityskill;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/opportunity-skills")
public class OpportunitySkillController {

    private final OpportunitySkillService service;

    public OpportunitySkillController(
            OpportunitySkillService service) {

        this.service = service;
    }

    @GetMapping("/{opportunityId}")
    public List<OpportunitySkillResponse> getRequiredSkills(
            @PathVariable Long opportunityId) {

        return service.getRequiredSkills(opportunityId);
    }
}