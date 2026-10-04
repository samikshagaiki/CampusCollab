package com.campuscollab.opportunityskill;

public class OpportunitySkillResponse {

    private Long skillId;
    private String skillName;

    public OpportunitySkillResponse(
            Long skillId,
            String skillName) {

        this.skillId = skillId;
        this.skillName = skillName;
    }

    public Long getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }
}