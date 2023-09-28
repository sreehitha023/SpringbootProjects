package com.msf.test.repository;

import com.msf.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    List<User> findByUserName(String userName);
}
