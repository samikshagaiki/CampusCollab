package com.campuscollab.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.campuscollab.dto.OpportunityRequest;
import com.campuscollab.dto.OpportunityResponse;
import com.campuscollab.service.CollaborationOpportunityService;

@RestController
@RequestMapping("/api/opportunities")
public class CollaborationOpportunityController {

    private final CollaborationOpportunityService opportunityService;

    public CollaborationOpportunityController(
            CollaborationOpportunityService opportunityService) {

        this.opportunityService = opportunityService;
    }

    @PostMapping
    public OpportunityResponse createOpportunity(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody OpportunityRequest request) {

        String email = jwt.getSubject();

        return opportunityService.createOpportunity(
                email,
                request
        );
    }

    @GetMapping
    public List<OpportunityResponse> getOpenOpportunities() {

        return opportunityService.getOpenOpportunities();
    }

    @GetMapping("/me")
    public List<OpportunityResponse> getMyOpportunities(
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getSubject();

        return opportunityService.getMyOpportunities(email);
    }
}