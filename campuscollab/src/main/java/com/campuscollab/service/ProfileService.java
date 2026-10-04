package com.campuscollab.service;

import org.springframework.stereotype.Service;

import com.campuscollab.dto.ProfileRequest;
import com.campuscollab.dto.ProfileResponse;
import com.campuscollab.entity.StudentProfile;
import com.campuscollab.entity.User;
import com.campuscollab.repository.StudentProfileRepository;
import com.campuscollab.repository.UserRepository;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final StudentProfileRepository profileRepository;

    public ProfileService(
            UserRepository userRepository,
            StudentProfileRepository profileRepository) {

        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public ProfileResponse createProfile(
            String email,
            ProfileRequest request) {

        // Find the logged-in user using the email from JWT
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Check if the user already has a profile
        if (profileRepository.findByUserId(user.getId()).isPresent()) {
            throw new RuntimeException("Profile already exists");
        }

        // Create new profile
        StudentProfile profile = new StudentProfile();

        profile.setUser(user);
        profile.setBio(request.getBio());
        profile.setBranch(request.getBranch());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setInterests(request.getInterests());
        profile.setAvailability(request.getAvailability());

        // Save profile to PostgreSQL
        StudentProfile savedProfile = profileRepository.save(profile);

        // Return DTO instead of returning StudentProfile entity
        return new ProfileResponse(
                savedProfile.getId(),
                user.getName(),
                user.getEmail(),
                savedProfile.getBio(),
                savedProfile.getBranch(),
                savedProfile.getGraduationYear(),
                savedProfile.getInterests(),
                savedProfile.getAvailability()
        );
        
        
    }
    
    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Profile not found"));

        return new ProfileResponse(
                profile.getId(),
                user.getName(),
                user.getEmail(),
                profile.getBio(),
                profile.getBranch(),
                profile.getGraduationYear(),
                profile.getInterests(),
                profile.getAvailability()
        );
    }
    
    public ProfileResponse updateProfile(
            String email,
            ProfileRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Profile not found"));

        profile.setBio(request.getBio());
        profile.setBranch(request.getBranch());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setInterests(request.getInterests());
        profile.setAvailability(request.getAvailability());

        StudentProfile updatedProfile = profileRepository.save(profile);

        return new ProfileResponse(
                updatedProfile.getId(),
                user.getName(),
                user.getEmail(),
                updatedProfile.getBio(),
                updatedProfile.getBranch(),
                updatedProfile.getGraduationYear(),
                updatedProfile.getInterests(),
                updatedProfile.getAvailability()
        );
    }
}