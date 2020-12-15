package com.yurifedotov.userregistry.web;

import com.yurifedotov.userregistry.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final List<User> userList = new ArrayList<>();

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userList;
    }

    @GetMapping(value = "{userId}")
    public User getAllUserById(@PathVariable String userId) {
        return userList.stream()
                .filter(u -> u.getId().equals(userId))
                .findAny()
                .orElse(null);
    }

    @PostMapping("")
    public void saveUsers(@RequestBody List<User> users) {
        userList.addAll(users);
    }

    @GetMapping("search")
    public List<User> findUsers(@RequestParam("query") String query) {
        return userList.subList(0, 1);
    }
}
