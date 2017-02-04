package com.progressoft.security.jpa.gateway;

import com.progressoft.security.jpa.entity.UserChainEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChainsGateway extends CrudRepository<UserChainEntity, Long> {
    List<UserChainEntity> findByUsernameOrderByChainOrderAsc(String username);
}
