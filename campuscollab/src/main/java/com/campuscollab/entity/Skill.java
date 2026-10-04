package com.campuscollab.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "skills",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}