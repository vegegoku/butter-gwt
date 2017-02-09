package com.progressoft.security.login.server.repository;

import com.progressoft.security.config.ContextInitializer;
import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.model.user.User;
import com.progressoft.security.repository.JpaUserRepository;
import com.progressoft.security.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JpaUserRepositoryTest {

    public static final String WRONG_TENANT = "WRONG_TENANT";
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        ContextInitializer.initialize();
        repository = new JpaUserRepository();

        GatewayContext.userGateway().deleteAll();

        UserEntity userEntity=new UserEntity();
        userEntity.username="FOUND_USER";
        userEntity.secret="SECRET";
        userEntity.tenant="TENANT";

        GatewayContext.userGateway().save(userEntity);

    }

    @Test
    public void givenRepository_WhenFindUserPassingUserNotFound_ShouldReturnNull() throws Exception {
        assertNull(repository.findUser("NOT_FOUND", WRONG_TENANT));
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndWrongTenant_ShouldReturnNull() throws Exception {
        assertNull(repository.findUser("FOUND_USER", WRONG_TENANT));
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndTenant_ShouldReturnTheUser() throws Exception {
        assertEquals(new User.UserBuilder().name("FOUND_USER").secret("SECRET").tenant("TENANT").email("found.user@mail.com").build(), repository.findUser("FOUND_USER", "TENANT"));
    }
}
