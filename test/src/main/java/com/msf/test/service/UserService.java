package com.msf.test.service;

import com.msf.test.entity.User;

import java.util.List;

public interface UserService {
    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(Long userId);
    public User getUser(Long userId);
    public List<User> getAllUsers();
    public List<User> getByUserName(String userName);
}
