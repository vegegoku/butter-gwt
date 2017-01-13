package com.progressoft.security.login.server.repository;

import com.progressoft.security.login.server.model.User;

@FunctionalInterface
public interface UserRepository {
    User findUser(String name, String tenant);
}
