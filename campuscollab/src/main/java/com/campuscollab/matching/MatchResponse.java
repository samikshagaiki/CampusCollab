package com.campuscollab.matching;

import java.util.List;

public class MatchResponse {

    private Long userId;
    private String name;
    private double matchScore;
    private List<String> matchedSkills;

    public MatchResponse(
            Long userId,
            String name,
            double matchScore,
            List<String> matchedSkills) {

        this.userId = userId;
        this.name = name;
        this.matchScore = matchScore;
        this.matchedSkills = matchedSkills;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMatchScore(double matchScore) {
		this.matchScore = matchScore;
	}

	public void setMatchedSkills(List<String> matchedSkills) {
		this.matchedSkills = matchedSkills;
	}
}