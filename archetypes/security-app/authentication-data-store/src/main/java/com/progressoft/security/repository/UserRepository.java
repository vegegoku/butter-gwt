package com.progressoft.security.repository;


import com.progressoft.security.model.user.User;

@FunctionalInterface
public interface UserRepository {
    User findUser(String name, String tenant);
}
