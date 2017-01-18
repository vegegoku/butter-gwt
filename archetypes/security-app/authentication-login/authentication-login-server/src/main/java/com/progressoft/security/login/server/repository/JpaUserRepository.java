package com.progressoft.security.login.server.repository;

import com.progressoft.security.jpa.StoreContext;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.store.UserStore;
import com.progressoft.security.login.server.model.User;


import static java.util.Objects.*;

public class JpaUserRepository implements UserRepository {

    @Override
    public User findUser(String name, String tenant) {
        return makeUser(repository().findByUsernameAndTenant(name, tenant));
    }

    private UserStore repository(){
        return StoreContext.userStore();
    }

    private User makeUser(UserEntity userEntity) {
        if(isNull(userEntity))
            return null;
        return new User(userEntity.username, userEntity.secret, userEntity.tenant);
    }
}
