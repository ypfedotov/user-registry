package com.yurifedotov.userregistry.service;

import com.yurifedotov.userregistry.dataaccess.UserRepository;
import com.yurifedotov.userregistry.dataaccess.UserSearchRepository;
import com.yurifedotov.userregistry.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserSearchRepository searchRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSearchRepository(UserSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(User user) throws UserValidationException {
        validateUser(user);
        userRepository.save(user);
        searchRepository.index(user);
    }

    private void validateUser(User user) throws UserValidationException {
        Map<String, String> validationErrors = new HashMap<>();
        if (isBlank(user.getFullName())) {
            validationErrors.put("fullName", "Full name is required");
        }

        if (isBlank(user.getEmail())) {
            validationErrors.put("email", "Email is required");
        } else if (userRepository.emailExists(user.getEmail())) {
            validationErrors.put("email", "User with this email already exists");
        }

        if (isBlank(user.getUsername())) {
            validationErrors.put("username", "username is required");
        } else if (userRepository.usernameExists(user.getUsername())) {
            validationErrors.put("username", "User with this username already exists");
        }

        if (!validationErrors.isEmpty()) {
            throw new UserValidationException(validationErrors);
        }
    }

    public List<User> searchUsersByName(String query) {
        List<String> matchingUserIds = searchRepository.findUserIdsByName(query);
        if (matchingUserIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<User> matchingUsers = userRepository.findByIds(matchingUserIds);
        Map<String, User> usersById = matchingUsers.stream().collect(Collectors.toMap(User::getId, user -> user));

        return matchingUserIds.stream()
                .map(usersById::get)
                .collect(Collectors.toList());
    }
}
