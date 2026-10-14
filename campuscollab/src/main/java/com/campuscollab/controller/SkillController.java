package com.campuscollab.controller;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.campuscollab.dto.SkillRequest;
import com.campuscollab.dto.SkillResponse;
import com.campuscollab.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillResponse addSkill(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody SkillRequest request) {
        return skillService.addSkill(jwt.getSubject(), request);
    }

    @GetMapping("/me")
    public List<SkillResponse> getMySkills(
            @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getSubject();

        return skillService.getMySkills(email);
    }
}