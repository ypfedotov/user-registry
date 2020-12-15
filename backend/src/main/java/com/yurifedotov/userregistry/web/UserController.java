package com.yurifedotov.userregistry.web;

import com.yurifedotov.userregistry.model.User;
import com.yurifedotov.userregistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("")
    public void saveUsers(@RequestBody List<User> users) {
        userService.saveUsers(users);
    }

    @GetMapping("search")
    public List<User> searchUsersByName(@RequestParam("query") String query) {
        return userService.searchUsersByName(query);
    }
}
