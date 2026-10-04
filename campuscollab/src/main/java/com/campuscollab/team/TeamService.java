package com.campuscollab.team;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campuscollab.entity.User;

@Service
public class TeamService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamService(
            TeamMemberRepository teamMemberRepository) {

        this.teamMemberRepository = teamMemberRepository;
    }

    public List<TeamResponse> getTeam(Long opportunityId) {

        return teamMemberRepository
                .findByOpportunityId(opportunityId)
                .stream()
                .map(member -> {

                    User user = member.getUser();

                    return new TeamResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            member.getRole()
                    );
                })
                .toList();
    }
}