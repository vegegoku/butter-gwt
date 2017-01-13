package com.progressoft.security.login.client;

import com.progressoft.security.login.server.model.User;
import com.progressoft.security.login.server.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<String, User>(){{
        put("FOUND_USER/TENANT", new User("FOUND_USER","SECRET", "TENANT"));
    }};

    @Override
    public User findUser(String name, String tenant) {
        return users.get(name+"/"+tenant);
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
