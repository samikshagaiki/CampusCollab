package com.campuscollab.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campuscollab.dto.OpportunityRequest;
import com.campuscollab.dto.OpportunityResponse;
import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.entity.OpportunityStatus;
import com.campuscollab.entity.User;
import com.campuscollab.repository.CollaborationOpportunityRepository;
import com.campuscollab.repository.UserRepository;
import com.campuscollab.team.TeamMember;
import com.campuscollab.team.TeamRole;
import com.campuscollab.team.TeamMemberRepository;
import com.campuscollab.entity.Skill;
import com.campuscollab.opportunityskill.OpportunitySkill;
import com.campuscollab.opportunityskill.OpportunitySkillRepository;
import com.campuscollab.repository.SkillRepository;

@Service
public class CollaborationOpportunityService {

    private final UserRepository userRepository;
    private final CollaborationOpportunityRepository opportunityRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final SkillRepository skillRepository;
    private final OpportunitySkillRepository opportunitySkillRepository;

    public CollaborationOpportunityService(
        UserRepository userRepository,
        CollaborationOpportunityRepository opportunityRepository,
        TeamMemberRepository teamMemberRepository,
        SkillRepository skillRepository,
        OpportunitySkillRepository opportunitySkillRepository) {

    this.userRepository = userRepository;
    this.opportunityRepository = opportunityRepository;
    this.teamMemberRepository = teamMemberRepository;
    this.skillRepository = skillRepository;
    this.opportunitySkillRepository = opportunitySkillRepository;
}

    public OpportunityResponse createOpportunity(
            String email,
            OpportunityRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CollaborationOpportunity opportunity =
                new CollaborationOpportunity();

        opportunity.setOwner(user);
        opportunity.setTitle(request.getTitle());
        opportunity.setDescription(request.getDescription());
        opportunity.setTeamSize(request.getTeamSize());

        // Backend controls these values
        opportunity.setStatus(OpportunityStatus.OPEN);
        opportunity.setCreatedAt(LocalDateTime.now());

        CollaborationOpportunity savedOpportunity =
                opportunityRepository.save(opportunity);
        
        TeamMember ownerMember = new TeamMember();

        ownerMember.setOpportunity(savedOpportunity);
        ownerMember.setUser(user);
        ownerMember.setRole(TeamRole.OWNER);

        teamMemberRepository.save(ownerMember);
        
        if (request.getRequiredSkills() != null) {

            for (String skillName : request.getRequiredSkills()) {

                Skill skill = skillRepository
                        .findByNameIgnoreCase(skillName)
                        .orElseGet(() -> {

                            Skill newSkill = new Skill();
                            newSkill.setName(skillName.trim());

                            return skillRepository.save(newSkill);
                        });

                OpportunitySkill opportunitySkill =
                        new OpportunitySkill();

                opportunitySkill.setOpportunity(savedOpportunity);
                opportunitySkill.setSkill(skill);

                opportunitySkillRepository.save(opportunitySkill);
            }
        }
        

        return toResponse(savedOpportunity);
    }

    public List<OpportunityResponse> getOpenOpportunities() {

        return opportunityRepository
                .findByStatus(OpportunityStatus.OPEN)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<OpportunityResponse> getMyOpportunities(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return opportunityRepository
                .findByOwnerId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private OpportunityResponse toResponse(
            CollaborationOpportunity opportunity) {

        return new OpportunityResponse(
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getDescription(),
                opportunity.getTeamSize(),
                opportunity.getOwner().getId(),
                opportunity.getOwner().getName(),
                opportunity.getStatus(),
                opportunity.getCreatedAt()
        );
    }
}