package com.progressoft.security.repository;


import com.progressoft.security.model.user.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<String, User>(){{
        put("FOUND_USER/TENANT", new User.UserBuilder().name("FOUND_USER").secret("SECRET").tenant("TENANT").email("found.user@mail.com").displayName("found user").build());
    }};

    @Override
    public User findUser(String name, String tenant) {
        return users.get(name+"/"+tenant);
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
