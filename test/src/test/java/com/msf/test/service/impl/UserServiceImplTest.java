package com.msf.test.service.impl;

import com.msf.test.entity.User;
import com.msf.test.repository.UserRepository;
import com.msf.test.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    UserService userService;
    AutoCloseable autoCloseable;
    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        user= new User(1L,"Sree","USA","8328660757");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.createUser(user)).isEqualTo("Success");

    }

    @Test
    void updateUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.updateUser(user)).isEqualTo("Success");
    }

    @Test
    void deleteUser() {
        mock(User.class);
        mock(UserRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(userRepository)
                .deleteById(any());
        assertThat(userService.deleteUser(1L)).isEqualTo("Success");
    }

    @Test
    void getUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUser(1L).getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    void getAllUsers() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(new ArrayList<User>(
                Collections.singleton(user)
        ));

        assertThat(userService.getAllUsers().get(0).getUserPhoneNumber()).
                isEqualTo(user.getUserPhoneNumber());

    }

    @Test
    void getByUserName() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findByUserName("Sreehitha")).
                thenReturn(new ArrayList<User>(Collections.singleton(user)));

        assertThat(userService.getByUserName("Sreehitha").get(0).getUserId()).
                isEqualTo(user.getUserId());
    }
}