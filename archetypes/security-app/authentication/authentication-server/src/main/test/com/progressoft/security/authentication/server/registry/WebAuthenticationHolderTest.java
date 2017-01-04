package com.progressoft.security.authentication.server.registry;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;

public class WebAuthenticationHolderTest {

    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        session = createMock(HttpSession.class);
    }

    @Test
    public void canCreateWebAuthenticationHolder() throws Exception {
        new WebAuthenticationHolder(session);
    }

    @Test
    public void givenHolder_WhenSessionHasUser_ThenIsEmptyShouldReturnFalse() throws Exception {
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(new Principal() {
        });
        replay(session);
        assertFalse(new WebAuthenticationHolder(session).isEmpty());
    }

    @Test
    public void givenHolder_WhenSessionHasNoUser_ThenIsEmptyShouldReturnTrue() throws Exception {
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(null);
        replay(session);
        assertTrue(new WebAuthenticationHolder(session).isEmpty());
    }

    @Test
    public void givenHolder_WhenSessionHasUser_ThenGetPrincipalShouldReturnPrincipal() throws Exception {
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(new Principal() {
        });
        replay(session);
        assertNotNull(new WebAuthenticationHolder(session).getPrincipal());
    }

    @Test
    public void givenHolder_WhenSessionHasNoUser_ThenGetPrincipalShouldReturnNull() throws Exception {
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(null);
        replay(session);
        assertNull(new WebAuthenticationHolder(session).getPrincipal());
    }
}
