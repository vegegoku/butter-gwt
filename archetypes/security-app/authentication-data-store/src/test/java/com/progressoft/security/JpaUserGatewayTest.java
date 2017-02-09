package com.progressoft.security;

import com.progressoft.security.config.ContextConfig;
import com.progressoft.security.config.ContextInitializer;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.gateway.Gateways;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContextConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class JpaUserGatewayTest {

    public static final String WRONG_TENANT = "WRONG_TENANT";

    @Autowired
    private Gateways gateways;

    private UserEntity foundUser;

    @Before
    public void setUp() throws Exception {
        gateways.getUserGateway().deleteAll();

        foundUser = new UserEntity();
        foundUser.username = "FOUND_USER";
        foundUser.secret = "SECRET";
        foundUser.tenant = "TENANT";

        gateways.getUserGateway().save(foundUser);
    }

    @Test
    public void givenRepository_WhenFindUserPassingUserNotFound_ShouldReturnNull() throws Exception {
        assertNull(gateways.getUserGateway().findByUsernameAndTenant("NOT_FOUND", WRONG_TENANT));
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndWrongTenant_ShouldReturnNull() throws Exception {
        assertNull(gateways.getUserGateway().findByUsernameAndTenant("FOUND_USER", WRONG_TENANT));
    }

    @Test
    public void givenRepository_WhenFindUserPassingFoundUserNameAndTenant_ShouldReturnTheUser() throws Exception {
        assertEquals(foundUser, gateways.getUserGateway().findByUsernameAndTenant("FOUND_USER", "TENANT"));
    }
}
