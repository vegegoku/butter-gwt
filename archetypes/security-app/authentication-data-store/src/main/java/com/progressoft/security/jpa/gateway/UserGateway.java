package com.progressoft.security.jpa.gateway;

import com.progressoft.security.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGateway extends CrudRepository<UserEntity, Long>{
    UserEntity findById(long id);
    UserEntity findByUsernameAndTenant(String username, String tenant);
}
