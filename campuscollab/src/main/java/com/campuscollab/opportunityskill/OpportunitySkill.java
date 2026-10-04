package com.campuscollab.opportunityskill;

import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.entity.Skill;

import jakarta.persistence.*;

@Entity
@Table(
    name = "opportunity_skills",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"opportunity_id", "skill_id"}
        )
    }
)
public class OpportunitySkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "opportunity_id",
        nullable = false
    )
    private CollaborationOpportunity opportunity;

    @ManyToOne
    @JoinColumn(
        name = "skill_id",
        nullable = false
    )
    private Skill skill;

    public OpportunitySkill() {
    }

    public Long getId() {
        return id;
    }

    public CollaborationOpportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(
            CollaborationOpportunity opportunity) {
        this.opportunity = opportunity;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}