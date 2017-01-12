package com.progressoft.security.authentication.server.registry;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.authentication.shared.registry.AuthenticationHolder;

import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

public class WebAuthenticationHolder implements AuthenticationHolder {
    static final String PRINCIPLE = "principle";
    private final HttpSession session;

    public WebAuthenticationHolder(HttpSession session) {
        this.session = session;
    }

    public WebAuthenticationHolder(HttpSession session, Principal principal) {
        this.session = session;
        this.session.setAttribute(PRINCIPLE, principal);
    }

    @Override
    public boolean isEmpty() {
        return isNull(session.getAttribute(PRINCIPLE));
    }

    @Override
    public Principal getPrincipal() {
        return (Principal) session.getAttribute(PRINCIPLE);
    }
}
