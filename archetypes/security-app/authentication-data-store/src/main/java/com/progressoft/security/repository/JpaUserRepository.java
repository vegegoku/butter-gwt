package com.progressoft.security.repository;

import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.gateway.UserGateway;
import com.progressoft.security.model.user.User;

import static java.util.Objects.isNull;

public class JpaUserRepository implements UserRepository {

    @Override
    public User findUser(String name, String tenant) {
        return makeUser(repository().findByUsernameAndTenant(name, tenant));
    }

    private UserGateway repository() {
        return GatewayContext.userGateway();
    }

    private User makeUser(UserEntity userEntity) {
        if (isNull(userEntity))
            return null;
        return new User.UserBuilder()
                .name(userEntity.username)
                .secret(userEntity.secret)
                .tenant(userEntity.tenant)
                .email(userEntity.email).build();
    }
}
