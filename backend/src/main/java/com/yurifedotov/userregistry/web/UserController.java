package com.yurifedotov.userregistry.web;

import com.yurifedotov.userregistry.model.User;
import com.yurifedotov.userregistry.service.UserService;
import com.yurifedotov.userregistry.service.UserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> saveUser(@RequestBody User users) {
        try {
            userService.saveUser(users);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (UserValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getValidationErrors());        }
    }

    @GetMapping("search")
    public List<User> searchUsersByName(@RequestParam("query") String query) {
        return userService.searchUsersByName(query);
    }
}
