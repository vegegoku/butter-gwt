package com.progressoft.security.authentication.server.filter;

import com.progressoft.security.authentication.server.authentication.WebSessionHolder;
import com.progressoft.security.authentication.server.shared.AuthenticationProcessContext;
import com.progressoft.security.authentication.server.shared.UserSessionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //there is nothing to do for initialization.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UserSessionContext.setContext(WebSessionHolder.makeUserSessionHolder(((HttpServletRequest) request).getSession()));
        AuthenticationProcessContext.setContext(WebSessionHolder.makeAuthenticationSessionHolder(((HttpServletRequest) request).getSession()));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        UserSessionContext.clear();
        AuthenticationProcessContext.clear();
    }
}
