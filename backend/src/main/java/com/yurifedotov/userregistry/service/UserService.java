package com.yurifedotov.userregistry.service;

import com.yurifedotov.userregistry.dataaccess.UserRepository;
import com.yurifedotov.userregistry.dataaccess.UserSearchRepository;
import com.yurifedotov.userregistry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void saveUsers(List<User> users) {
        if (users.isEmpty()) {
            return;
        }
        userRepository.save(users);
        searchRepository.index(users);
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
