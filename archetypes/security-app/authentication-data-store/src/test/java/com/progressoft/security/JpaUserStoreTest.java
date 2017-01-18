package com.progressoft.security;

import com.progressoft.security.config.ContextConfig;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.store.Stores;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContextConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JpaUserStoreTest {

    public static final String WRONG_TENANT = "WRONG_TENANT";

    @Autowired
    private Stores stores;

    private UserEntity foundUser;

    @Before
    public void setUp() throws Exception {
        stores.getUserStore().deleteAll();

        foundUser =new UserEntity();
        foundUser.username="FOUND_USER";
        foundUser.secret="SECRET";
        foundUser.tenant="TENANT";

        stores.getUserStore().save(foundUser);
    }

    @Test
    public void givenRepository_WhenFindUserPassingUserNotFound_ShouldReturnNull() throws Exception {
        UserEntity u= stores.getUserStore().findByUsernameAndTenant("NOT_FOUND", WRONG_TENANT);
        System.out.println(u);
        assertNull(u);
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndWrongTenant_ShouldReturnNull() throws Exception {
        assertNull(stores.getUserStore().findByUsernameAndTenant("FOUND_USER", WRONG_TENANT));
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndTenant_ShouldReturnTheUser() throws Exception {
        assertEquals(foundUser, stores.getUserStore().findByUsernameAndTenant("FOUND_USER", "TENANT"));
    }
}
