package com.campuscollab.team;

public class TeamResponse {

    private Long userId;
    private String name;
    private String email;
    private TeamRole role;

    public TeamResponse(
            Long userId,
            String name,
            String email,
            TeamRole role) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public TeamRole getRole() {
        return role;
    }
}