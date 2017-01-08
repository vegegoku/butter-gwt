package org.akab.engine.app.test;

import org.akab.engine.core.server.RpcEntryPointContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestEntryPointContext extends RpcEntryPointContext{
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpServletResponse;

    public TestEntryPointContext() {
        super(null, null);
    }

    @Override
    public HttpServletRequest getHttpRequest() {
        return this.httpRequest;
    }

    @Override
    public HttpServletResponse getHttpServletResponse() {
        return this.httpServletResponse;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

}
