package com.campuscollab.service;

import org.springframework.stereotype.Service;

import com.campuscollab.entity.User;
import com.campuscollab.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}