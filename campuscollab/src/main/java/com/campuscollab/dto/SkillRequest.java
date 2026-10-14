package com.campuscollab.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import com.campuscollab.entity.SkillType;

public class SkillRequest {

	@NotBlank(message = "Skill name is required")
	@Size(max = 100, message = "Skill name must not exceed 100 characters")
	private String skillName;

	@NotNull(message = "Skill type is required")
	private SkillType type;

	@NotNull(message = "Proficiency is required")
	@Min(value = 1, message = "Proficiency must be between 1 and 5")
	@Max(value = 5, message = "Proficiency must be between 1 and 5")
	private Integer proficiency;	


    public SkillRequest() {
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }
}