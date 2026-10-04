package com.campuscollab.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.campuscollab.dto.ApplicationRequest;
import com.campuscollab.dto.ApplicationResponse;
import com.campuscollab.service.CollaborationApplicationService;

@RestController
@RequestMapping("/api/applications")
public class CollaborationApplicationController {

    private final CollaborationApplicationService applicationService;

    public CollaborationApplicationController(
            CollaborationApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @PostMapping("/opportunity/{opportunityId}")
    public ApplicationResponse apply(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long opportunityId,
            @RequestBody ApplicationRequest request) {

        String email = jwt.getSubject();

        return applicationService.apply(
                email,
                opportunityId,
                request
        );
    }

    @GetMapping("/me")
    public List<ApplicationResponse> getMyApplications(
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getSubject();

        return applicationService.getMyApplications(email);
    }

    @GetMapping("/opportunity/{opportunityId}")
    public List<ApplicationResponse> getApplicationsForOpportunity(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long opportunityId) {

        String email = jwt.getSubject();

        return applicationService
                .getApplicationsForOpportunity(
                        email,
                        opportunityId
                );
    }

    @PutMapping("/{applicationId}/accept")
    public ApplicationResponse acceptApplication(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long applicationId) {

        String email = jwt.getSubject();

        return applicationService.acceptApplication(
                email,
                applicationId
        );
    }

    @PutMapping("/{applicationId}/reject")
    public ApplicationResponse rejectApplication(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long applicationId) {

        String email = jwt.getSubject();

        return applicationService.rejectApplication(
                email,
                applicationId
        );
    }
}