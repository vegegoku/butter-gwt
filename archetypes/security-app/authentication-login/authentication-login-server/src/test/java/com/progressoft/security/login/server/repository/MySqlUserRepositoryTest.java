package com.progressoft.security.login.server.repository;

import com.progressoft.security.JpaStoresLoader;
import com.progressoft.security.jpa.StoreContext;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.login.server.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MySqlUserRepositoryTest {

    public static final String WRONG_TENANT = "WRONG_TENANT";
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new JpaUserRepository();
        JpaStoresLoader.load();

        StoreContext.userStore().deleteAll();

        UserEntity userEntity=new UserEntity();
        userEntity.username="FOUND_USER";
        userEntity.secret="SECRET";
        userEntity.tenant="TENANT";

        StoreContext.userStore().save(userEntity);

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
        assertEquals(new User("FOUND_USER", "SECRET", "TENANT"), repository.findUser("FOUND_USER", "TENANT"));
    }
}
