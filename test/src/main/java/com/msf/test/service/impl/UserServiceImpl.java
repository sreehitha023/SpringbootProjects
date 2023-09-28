package com.msf.test.service.impl;

import com.msf.test.entity.User;
import com.msf.test.exception.UserNotFoundException;
import com.msf.test.repository.UserRepository;
import com.msf.test.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return "Success";
    }

    @Override
    public User getUser(Long userId) {
        if(userRepository.findById(userId).isEmpty())
            throw new UserNotFoundException("Requested User does not exist");
        return userRepository.findById(userId).get();
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
