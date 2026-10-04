package com.campuscollab.team;

import com.campuscollab.entity.CollaborationOpportunity;
import com.campuscollab.entity.User;

import jakarta.persistence.*;

@Entity
@Table(
    name = "team_members",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"opportunity_id", "user_id"}
        )
    }
)
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "opportunity_id", nullable = false)
    private CollaborationOpportunity opportunity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamRole role;

    public TeamMember() {
    }

    public Long getId() {
        return id;
    }

    public CollaborationOpportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(CollaborationOpportunity opportunity) {
        this.opportunity = opportunity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeamRole getRole() {
        return role;
    }

    public void setRole(TeamRole role) {
        this.role = role;
    }
}