package com.campuscollab.dto;

public class ProfileResponse {

    private Long id;
    private String name;
    private String email;
    private String bio;
    private String branch;
    private Integer graduationYear;
    private String interests;
    private String availability;

    public ProfileResponse() {
    }

    public ProfileResponse(
            Long id,
            String name,
            String email,
            String bio,
            String branch,
            Integer graduationYear,
            String interests,
            String availability) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.branch = branch;
        this.graduationYear = graduationYear;
        this.interests = interests;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getBranch() {
        return branch;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public String getInterests() {
        return interests;
    }

    public String getAvailability() {
        return availability;
    }
}