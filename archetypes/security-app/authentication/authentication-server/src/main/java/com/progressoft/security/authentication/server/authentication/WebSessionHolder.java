package com.progressoft.security.authentication.server.authentication;

import com.progressoft.security.authentication.server.shared.AuthenticationProcessContext;
import com.progressoft.security.authentication.server.shared.UserSessionContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

public class WebSessionHolder implements AuthenticationHolder {

    private final HttpSession session;
    private final String sessionProperty;

    private WebSessionHolder(HttpSession session, String sessionProperty) {
        this.session=session;
        this.sessionProperty = sessionProperty;
    }

    @Override
    public void reset() {
        session.invalidate();
    }

    @Override
    public boolean isEmpty() {
        return isNull(session.getAttribute(sessionProperty));
    }

    @Override
    public Principal getPrincipal() {
        return (Principal) session.getAttribute(sessionProperty);
    }

    @Override
    public void setProperty(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public <T> T getProperty(String key) {
        return (T)session.getAttribute(key);
    }

    public static WebSessionHolder makeUserSessionHolder(HttpSession session){
        return new WebSessionHolder(session, UserSessionContext.PRINCIPAL);
    }

    public static WebSessionHolder makeAuthenticationSessionHolder(HttpSession session){
        return new WebSessionHolder(session, AuthenticationProcessContext.AUTHENTICATION);
    }
}
