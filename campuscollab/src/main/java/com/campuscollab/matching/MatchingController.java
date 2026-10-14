package com.campuscollab.matching;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matching")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/opportunity/{opportunityId}")
    public List<MatchResponse> findMatches(
            @PathVariable Long opportunityId) {

        return matchingService.findMatches(opportunityId);
    }
}