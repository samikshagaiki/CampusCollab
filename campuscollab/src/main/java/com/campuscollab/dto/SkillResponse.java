package com.campuscollab.dto;

import com.campuscollab.entity.SkillType;

public class SkillResponse {

    private Long id;
    private String skillName;
    private SkillType type;
    private Integer proficiency;

    public SkillResponse(
            Long id,
            String skillName,
            SkillType type,
            Integer proficiency) {

        this.id = id;
        this.skillName = skillName;
        this.type = type;
        this.proficiency = proficiency;
    }

    public Long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public SkillType getType() {
        return type;
    }

    public Integer getProficiency() {
        return proficiency;
    }
}