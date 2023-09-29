package com.msf.test.repository;

import com.msf.test.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(1L,"Sree","Chennai","8328660757");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    void findByUserName_found() {
        List<User> userList = userRepository.findByUserName("Sree");
        assertThat(userList.get(0).getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    void findByUserName(){
        List<User> userList = userRepository.findByUserName("Sreehitha");
        assertThat(userList.isEmpty()).isTrue();
    }
}