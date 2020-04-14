package com.SWE.OnlineStorePlatform.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findUserByEmail(String email);
}
