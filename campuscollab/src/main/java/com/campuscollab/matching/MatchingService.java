package com.campuscollab.matching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.campuscollab.entity.User;
import com.campuscollab.entity.UserSkill;
import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.repository.CollaborationOpportunityRepository;
import com.campuscollab.opportunityskill.OpportunitySkill;
import com.campuscollab.opportunityskill.OpportunitySkillRepository;
import com.campuscollab.repository.UserSkillRepository;
import com.campuscollab.repository.UserRepository;

@Service
public class MatchingService {

    private final UserRepository userRepository;
    private final CollaborationOpportunityRepository opportunityRepository;
    private final OpportunitySkillRepository opportunitySkillRepository;
    private final UserSkillRepository userSkillRepository;

    public MatchingService(
            UserRepository userRepository,
            CollaborationOpportunityRepository opportunityRepository,
            OpportunitySkillRepository opportunitySkillRepository,
            UserSkillRepository userSkillRepository) {

        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
        this.opportunitySkillRepository = opportunitySkillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    public List<MatchResponse> findMatches(Long opportunityId) {

        CollaborationOpportunity opportunity =
                opportunityRepository.findById(opportunityId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Opportunity not found"));

        List<OpportunitySkill> requiredSkills =
                opportunitySkillRepository
                        .findByOpportunityId(opportunityId);

        // Matching is not meaningful without required skills.
        if (requiredSkills.isEmpty()) {
            return List.of();
        }

        List<String> requiredSkillNames = requiredSkills.stream()
                .map(os -> os.getSkill().getName()
                        .trim().toLowerCase(Locale.ROOT))
                .distinct()
                .toList();

        List<MatchResponse> matches = new ArrayList<>();

        List<User> users = userRepository.findAll();

        for (User user : users) {

            // The owner should not be recommended for their own project.
            if (user.getId().equals(
                    opportunity.getOwner().getId())) {
                continue;
            }

            List<UserSkill> userSkills =
                    userSkillRepository.findByUserId(user.getId());

            List<String> offeredSkillNames = userSkills.stream()
                    .filter(us -> us.getType().name()
                            .equals("OFFERED"))
                    .map(us -> us.getSkill().getName()
                            .trim().toLowerCase(Locale.ROOT))
                    .distinct()
                    .toList();

            List<String> matchedSkills = requiredSkillNames.stream()
                    .filter(offeredSkillNames::contains)
                    .toList();

            if (matchedSkills.isEmpty()) {
                continue;
            }

            double score =
                    (matchedSkills.size() * 100.0)
                    / requiredSkillNames.size();

            List<String> displaySkills = requiredSkills.stream()
                    .map(os -> os.getSkill().getName())
                    .filter(name -> matchedSkills.contains(
                            name.trim().toLowerCase(Locale.ROOT)))
                    .distinct()
                    .toList();

            matches.add(new MatchResponse(
                    user.getId(),
                    user.getName(),
                    Math.round(score * 100.0) / 100.0,
                    displaySkills
            ));
        }

        matches.sort(
                Comparator.comparingDouble(
                        MatchResponse::getMatchScore
                ).reversed()
        );

        return matches;
    }
}