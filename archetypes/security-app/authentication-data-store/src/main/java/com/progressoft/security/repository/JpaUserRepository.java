package com.progressoft.security.repository;

import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.entity.UserChainEntity;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.gateway.ChainsGateway;
import com.progressoft.security.jpa.gateway.UserGateway;
import com.progressoft.security.model.user.User;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class JpaUserRepository implements UserRepository {

    @Override
    public User findUser(String name, String tenant) {
        UserEntity user = user(name, tenant);
        return makeUser(user, userChains(user));
    }

    private List<UserChainEntity> userChains(UserEntity user) {
        if (isNull(user) || user.username.trim().isEmpty())
            return Collections.emptyList();
        List<UserChainEntity> list = chainsRepository().findByUsernameOrderByChainOrderAsc(user.username);
        return list;
    }

    private UserEntity user(String name, String tenant) {
        return repository().findByUsernameAndTenant(name, tenant);
    }

    private ChainsGateway chainsRepository() {
        return GatewayContext.chainsGateway();
    }

    private UserGateway repository() {
        return GatewayContext.userGateway();
    }

    private User makeUser(UserEntity userEntity, List<UserChainEntity> chains) {
        if (isNull(userEntity))
            return null;

        Deque<String> chainsNames = toChainsNames(chains);
        return new User.UserBuilder()
                .name(userEntity.username)
                .secret(userEntity.secret)
                .tenant(userEntity.tenant)
                .email(userEntity.email)
                .displayName(userEntity.displayName)
                .chains(chainsNames).build();
    }

    protected Deque<String> toChainsNames(List<UserChainEntity> chains) {
        return chains.stream().map(chain -> chain.chainName).collect(Collectors.toCollection(LinkedList::new));
    }
}
