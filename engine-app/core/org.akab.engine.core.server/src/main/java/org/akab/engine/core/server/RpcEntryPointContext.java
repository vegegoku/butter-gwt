package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.ServerEntryPointContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RpcEntryPointContext implements ServerEntryPointContext{

    private final HttpServletRequest httpRequest;
    private final HttpServletResponse httpServletResponse;

    public RpcEntryPointContext(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        this.httpRequest = httpRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpServletResponse getHttpResponse() {
        return httpServletResponse;
    }

    @Override
    public String getName() {
        return RpcEntryPointContext.class.getCanonicalName();
    }

    public HttpSession getSession(){
        return getHttpRequest().getSession();
    }
}
