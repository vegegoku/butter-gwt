package com.progressoft.security.repository;

import com.progressoft.security.config.ContextConfig;
import com.progressoft.security.jpa.entity.UserChainEntity;
import com.progressoft.security.jpa.entity.UserEntity;
import com.progressoft.security.jpa.gateway.Gateways;
import com.progressoft.security.model.user.User;
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
public class JpaUserRepositoryTest {

    private static final String USER_HAS_CHAINS = "USER_HAS_CHAINS";
    private static final String TENANT = "TENANT";
    private static final String CHAIN = "CHAIN";
    private static final String SECOND_CHAIN = "SECOND_CHAIN";

    @Autowired
    private Gateways gateways;

    private UserEntity foundUser;
    private UserChainEntity chain;
    private UserChainEntity secondChain;
    private JpaUserRepositorySpy repositorySpy;

    @Before
    public void setUp() throws Exception {
        gateways.getUserGateway().deleteAll();
        gateways.getChainsGateway().deleteAll();

        foundUser = new UserEntity();
        foundUser.username = USER_HAS_CHAINS;
        foundUser.tenant = TENANT;

        gateways.getUserGateway().save(foundUser);

        chain = new UserChainEntity();
        chain.chainName = CHAIN;
        chain.username = USER_HAS_CHAINS;
        chain.chainOrder = 1;

        secondChain = new UserChainEntity();
        secondChain.chainName = SECOND_CHAIN;
        secondChain.username = USER_HAS_CHAINS;
        secondChain.chainOrder = 2;

        gateways.getChainsGateway().save(secondChain);
        gateways.getChainsGateway().save(chain);
        repositorySpy = new JpaUserRepositorySpy();
    }

    @Test
    public void givenUserRepository_WhenFindUserWhichHasNoChains_ShouldReturnTheUser() throws Exception {
        gateways.getChainsGateway().deleteAll();
        User user = repositorySpy.findUser(USER_HAS_CHAINS, TENANT);

        assertFalse(user.hasChains());
    }

    @Test
    public void givenUserRepository_WhenFindUserWhichHasOneChain_ShouldReturnTheUserWithItsChain() throws Exception {
        User user = repositorySpy.findUser(USER_HAS_CHAINS, TENANT);

        assertTrue(user.hasChains());
    }

    @Test
    public void givenUserRepository_WhenFindUserWhichHasMoreThanOneChain_ShouldReturnTheUserWithItsChainsInOrder() throws Exception {
        repositorySpy.findUser(USER_HAS_CHAINS, TENANT);

        assertEquals(CHAIN, repositorySpy.getChainsDeque().pop());
        assertEquals(SECOND_CHAIN, repositorySpy.getChainsDeque().pop());
    }
}
