package com.progressoft.security.repository;

import com.progressoft.security.jpa.entity.UserChainEntity;

import java.util.Deque;
import java.util.List;

public class JpaUserRepositorySpy extends JpaUserRepository {

    private Deque<String> chainsDeque;

    @Override
    protected Deque<String> chains(List<UserChainEntity> chains) {
        chainsDeque = super.chains(chains);
        return chainsDeque;
    }

    public Deque<String> getChainsDeque() {
        return chainsDeque;
    }
}
