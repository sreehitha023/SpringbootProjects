package com.msf.test.controller;

import com.msf.test.entity.User;
import com.msf.test.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    UserService userService;
    public UserController (UserService userService){
        this.userService=userService;
    }
    @GetMapping("/{userId}")
    public User getUserDetails(@PathVariable("userId") Long userId)
    {
        return userService.getUser(userId);
    }

    @GetMapping
    public List<User> getAllUserDetails()
    {
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUserDetails(@RequestBody User user)
    {
        userService.createUser(user);
        return "User Created Successfully";
    }

    @PutMapping
    public String updateUserDetails(@RequestBody User user)
    {
        userService.updateUser(user);
        return "User Updated Successfully";
    }

    @DeleteMapping("/{userId}")
    public String deleteUserDetails(@PathVariable("userId") Long userId)
    {
        userService.deleteUser(userId);
        return "User Deleted Successfully";
    }
}