package com.progressoft.security.authentication.server.filter;

import com.progressoft.security.authentication.server.authentication.WebAuthenticationHolder;
import com.progressoft.security.authentication.server.shared.UserSessionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserSessionContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //there is nothing to do for initialization.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UserSessionContext.setContext(new WebAuthenticationHolder(((HttpServletRequest) request).getSession()));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        UserSessionContext.clear();
    }
}
