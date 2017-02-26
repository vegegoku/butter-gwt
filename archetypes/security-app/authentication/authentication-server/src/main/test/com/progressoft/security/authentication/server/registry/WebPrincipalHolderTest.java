package com.progressoft.security.authentication.server.registry;

import com.progressoft.security.authentication.server.authentication.WebSessionHolder;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;
import org.akab.engine.core.test.FakeSession;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebPrincipalHolderTest {

    private Principal principal;
    private HttpSession session;
    private Map<String, Object> sessionAttributes;
    private AuthenticationHolder authenticationHolder;

    @Before
    public void setUp() throws Exception {
        principal = new Principal() {
            @Override
            public String getUserName() {
                return "";
            }

            @Override
            public String getTenant() {
                return "";
            }

            @Override
            public Deque<String> chains() {
                return null;
            }

            @Override
            public String getDisplayName() {
                return "";
            }
        };
        sessionAttributes=new HashMap<>();
        session=new FakeSession(sessionAttributes);
        authenticationHolder= WebSessionHolder.makeUserSessionHolder(session);
    }


    @Test
    public void givenHolder_WhenSessionHasUser_ThenIsEmptyShouldReturnFalse() throws Exception {
        session.setAttribute("principal", principal);
        assertFalse(authenticationHolder.isEmpty());
    }

    @Test
    public void givenHolder_WhenSessionHasNoUser_ThenIsEmptyShouldReturnTrue() throws Exception {
        assertTrue(authenticationHolder.isEmpty());
    }

    @Test
    public void givenHolder_WhenSessionHasUser_ThenGetPrincipalShouldReturnPrincipal() throws Exception {
        session.setAttribute("principal", principal);
        assertNotNull(authenticationHolder.getPrincipal());
    }

    @Test
    public void givenHolder_WhenSessionHasNoUser_ThenGetPrincipalShouldReturnNull() throws Exception {
        assertNull(authenticationHolder.getPrincipal());
    }
}
