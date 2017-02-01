package com.progressoft.security.authentication.server.authentication;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

public class WebAuthenticationHolder implements AuthenticationHolder {

    public static final String PRINCIPAL = "principal";
    private HttpSession session;

    public WebAuthenticationHolder(HttpSession session) {
        this.session=session;
    }

    @Override
    public boolean isEmpty() {
        return isNull(session.getAttribute(PRINCIPAL));
    }

    @Override
    public Principal getPrincipal() {
        return (Principal) session.getAttribute(PRINCIPAL);
    }

    @Override
    public void setProperty(String key, Object value) {
        session.setAttribute(key, value);
    }

    @Override
    public <T> T getProperty(String key) {
        return (T)session.getAttribute(key);
    }
}
