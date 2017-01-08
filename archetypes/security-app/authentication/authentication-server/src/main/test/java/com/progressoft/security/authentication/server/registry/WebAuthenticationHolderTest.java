package com.progressoft.security.authentication.server.registry;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class WebAuthenticationHolderTest {

    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        session = createStrictMock(HttpSession.class);
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
        Principal p=new Principal() {
        };
        session.setAttribute(isA(String.class), eq(p));
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(p);
        replay(session);
        assertNotNull(new WebAuthenticationHolder(session, p).getPrincipal());
    }

    @Test
    public void givenHolder_WhenSessionHasNoUser_ThenGetPrincipalShouldReturnNull() throws Exception {
        expect(session.getAttribute(WebAuthenticationHolder.PRINCIPLE)).andReturn(null);
        replay(session);
        assertNull(new WebAuthenticationHolder(session).getPrincipal());
    }
}
