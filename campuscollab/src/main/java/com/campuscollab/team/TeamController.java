package com.campuscollab.team;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{opportunityId}")
    public List<TeamResponse> getTeam(
            @PathVariable Long opportunityId) {

        return teamService.getTeam(opportunityId);
    }
}