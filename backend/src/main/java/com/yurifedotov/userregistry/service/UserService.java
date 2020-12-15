package com.yurifedotov.userregistry.service;

import com.yurifedotov.userregistry.dataaccess.UserRepository;
import com.yurifedotov.userregistry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void saveUsers(List<User> users) {
        if (users.isEmpty()) {
            return;
        }
        userRepository.save(users);
    }

    public List<User> searchUsersByName(String query) {
        return Collections.emptyList();
    }
}
