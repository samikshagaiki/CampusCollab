package com.campuscollab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campuscollab.entity.UserSkill;

public interface UserSkillRepository
        extends JpaRepository<UserSkill, Long> {

    List<UserSkill> findByUserId(Long userId);

    Optional<UserSkill> findByUserIdAndSkillIdAndType(
            Long userId,
            Long skillId,
            com.campuscollab.entity.SkillType type
    );
}