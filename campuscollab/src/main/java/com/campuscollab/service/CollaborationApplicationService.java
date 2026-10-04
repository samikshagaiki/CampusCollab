package com.campuscollab.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.campuscollab.dto.ApplicationRequest;
import com.campuscollab.dto.ApplicationResponse;
import com.campuscollab.entity.ApplicationStatus;
import com.campuscollab.entity.CollaborationApplication;
import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.entity.OpportunityStatus;
import com.campuscollab.entity.User;
import com.campuscollab.repository.CollaborationApplicationRepository;
import com.campuscollab.repository.CollaborationOpportunityRepository;
import com.campuscollab.repository.UserRepository;
import com.campuscollab.team.TeamMember;
import com.campuscollab.team.TeamMemberRepository;
import com.campuscollab.team.TeamRole;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CollaborationApplicationService {

    private final UserRepository userRepository;
    private final CollaborationOpportunityRepository opportunityRepository;
    private final CollaborationApplicationRepository applicationRepository;
    private final TeamMemberRepository teamMemberRepository;
    
    public CollaborationApplicationService(
            CollaborationApplicationRepository applicationRepository,
            UserRepository userRepository,
            CollaborationOpportunityRepository opportunityRepository,
            TeamMemberRepository teamMemberRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
        this.teamMemberRepository = teamMemberRepository;
    }
    public ApplicationResponse apply(
            String email,
            Long opportunityId,
            ApplicationRequest request) {

        User applicant = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CollaborationOpportunity opportunity =
                opportunityRepository.findById(opportunityId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Opportunity not found"));

        // Owner cannot apply to their own opportunity
        if (opportunity.getOwner().getId()
                .equals(applicant.getId())) {

            throw new RuntimeException(
                    "You cannot apply to your own opportunity");
        }

        // Only OPEN opportunities accept applications
        if (opportunity.getStatus()
                != OpportunityStatus.OPEN) {

            throw new RuntimeException(
                    "Opportunity is not open for applications");
        }

        // Prevent duplicate application
        if (applicationRepository
                .findByOpportunityIdAndApplicantId(
                        opportunityId,
                        applicant.getId())
                .isPresent()) {

            throw new RuntimeException(
                    "You have already applied");
        }

        CollaborationApplication application =
                new CollaborationApplication();

        application.setOpportunity(opportunity);
        application.setApplicant(applicant);
        application.setMessage(request.getMessage());
        application.setStatus(ApplicationStatus.PENDING);
        application.setCreatedAt(LocalDateTime.now());

        CollaborationApplication saved =
                applicationRepository.save(application);

        return toResponse(saved);
    }

    public List<ApplicationResponse> getMyApplications(
            String email) {

        User applicant = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return applicationRepository
                .findByApplicantId(applicant.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ApplicationResponse> getApplicationsForOpportunity(
            String email,
            Long opportunityId) {

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CollaborationOpportunity opportunity =
                opportunityRepository.findById(opportunityId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Opportunity not found"));

        // Only owner can see applications
        if (!opportunity.getOwner().getId()
                .equals(owner.getId())) {

            throw new RuntimeException(
                    "You are not the owner of this opportunity");
        }

        return applicationRepository
                .findByOpportunityId(opportunityId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

   @Transactional
   public ApplicationResponse acceptApplication(
        String email,
        Long applicationId) {

    User owner = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    CollaborationApplication application =
            applicationRepository.findById(applicationId)
            .orElseThrow(() ->
                    new RuntimeException("Application not found"));

    CollaborationOpportunity opportunity =
            application.getOpportunity();

    // Only opportunity owner can accept
    if (!opportunity.getOwner().getId().equals(owner.getId())) {
        throw new RuntimeException(
                "You are not the owner of this opportunity");
    }

    // Application must still be pending
    if (application.getStatus() != ApplicationStatus.PENDING) {
        throw new RuntimeException(
                "Application is already processed");
    }

    // Check team capacity
    long currentMembers =
            teamMemberRepository.countByOpportunityId(
                    opportunity.getId()
            );

    if (currentMembers >= opportunity.getTeamSize()) {
        throw new RuntimeException(
                "Team is already full");
    }

    // Accept application
    application.setStatus(ApplicationStatus.ACCEPTED);
    applicationRepository.save(application);

    // Add applicant to team
    TeamMember member = new TeamMember();

    member.setOpportunity(opportunity);
    member.setUser(application.getApplicant());
    member.setRole(TeamRole.MEMBER);

    teamMemberRepository.save(member);

    return toResponse(application);
}

    public ApplicationResponse rejectApplication(
            String email,
            Long applicationId) {

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CollaborationApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        CollaborationOpportunity opportunity =
                application.getOpportunity();

        // Only opportunity owner can reject
        if (!opportunity.getOwner().getId()
                .equals(owner.getId())) {

            throw new RuntimeException(
                    "Only the opportunity owner can reject applications");
        }

        if (application.getStatus()
                != ApplicationStatus.PENDING) {

            throw new RuntimeException(
                    "Application has already been processed");
        }

        application.setStatus(ApplicationStatus.REJECTED);

        CollaborationApplication saved =
                applicationRepository.save(application);

        return toResponse(saved);
    }

    private ApplicationResponse toResponse(
            CollaborationApplication application) {

        return new ApplicationResponse(
                application.getId(),
                application.getOpportunity().getId(),
                application.getOpportunity().getTitle(),
                application.getApplicant().getId(),
                application.getApplicant().getName(),
                application.getMessage(),
                application.getStatus(),
                application.getCreatedAt()
        );
    }
}