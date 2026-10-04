package com.campuscollab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.campuscollab.dto.ProfileRequest;
import com.campuscollab.dto.ProfileResponse;
import com.campuscollab.entity.StudentProfile;
import com.campuscollab.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ProfileResponse createProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody ProfileRequest request) {

        String email = jwt.getSubject();

        return profileService.createProfile(email, request);
    }
    
    @GetMapping("/me")
    public ProfileResponse getProfile(
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getSubject();

        return profileService.getProfile(email);
    }
    
    @PutMapping("/me")
    public ProfileResponse updateProfile(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody ProfileRequest request) {

        String email = jwt.getSubject();

        return profileService.updateProfile(email, request);
    }
}