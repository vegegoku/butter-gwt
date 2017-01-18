package com.progressoft.security.jpa.store;

import com.progressoft.security.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStore extends CrudRepository<UserEntity, Long>{
    UserEntity findById(long id);
    UserEntity findByUsernameAndTenant(String username, String tenant);
}
