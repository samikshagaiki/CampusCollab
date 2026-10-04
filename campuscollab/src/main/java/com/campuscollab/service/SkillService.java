package com.campuscollab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campuscollab.dto.SkillRequest;
import com.campuscollab.dto.SkillResponse;
import com.campuscollab.entity.Skill;
import com.campuscollab.entity.User;
import com.campuscollab.entity.UserSkill;
import com.campuscollab.repository.SkillRepository;
import com.campuscollab.repository.UserRepository;
import com.campuscollab.repository.UserSkillRepository;

@Service
public class SkillService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public SkillService(
            UserRepository userRepository,
            SkillRepository skillRepository,
            UserSkillRepository userSkillRepository) {

        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    public SkillResponse addSkill(
            String email,
            SkillRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Skill skill = skillRepository
                .findByNameIgnoreCase(request.getSkillName())
                .orElseGet(() -> {

                    Skill newSkill = new Skill();
                    newSkill.setName(request.getSkillName());

                    return skillRepository.save(newSkill);
                });

        if (userSkillRepository
                .findByUserIdAndSkillIdAndType(
                        user.getId(),
                        skill.getId(),
                        request.getType())
                .isPresent()) {

            throw new RuntimeException(
                    "Skill already added");
        }

        UserSkill userSkill = new UserSkill();

        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setType(request.getType());
        userSkill.setProficiency(request.getProficiency());

        UserSkill saved = userSkillRepository.save(userSkill);

        return new SkillResponse(
                saved.getId(),
                skill.getName(),
                saved.getType(),
                saved.getProficiency()
        );
    }

    public List<SkillResponse> getMySkills(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return userSkillRepository
                .findByUserId(user.getId())
                .stream()
                .map(userSkill -> new SkillResponse(
                        userSkill.getId(),
                        userSkill.getSkill().getName(),
                        userSkill.getType(),
                        userSkill.getProficiency()
                ))
                .toList();
    }
}