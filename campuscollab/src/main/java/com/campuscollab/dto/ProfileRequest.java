package com.campuscollab.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ProfileRequest {

	@Size(max = 500, message = "Bio must not exceed 500 characters")
	private String bio;

	@Size(max = 100, message = "Branch must not exceed 100 characters")
	private String branch;

	@Min(value = 2020, message = "Graduation year is invalid")
	@Max(value = 2100, message = "Graduation year is invalid")
	private Integer graduationYear;

	@Size(max = 500, message = "Interests must not exceed 500 characters")
	private String interests;

	@Size(max = 200, message = "Availability must not exceed 200 characters")
	private String availability;

    public ProfileRequest() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}