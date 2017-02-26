package com.progressoft.security.logout.server.handlers;

import com.progressoft.security.authentication.server.shared.UserSessionContext;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.logout.shared.response.LogoutResponse;
import com.progressoft.security.logout.shared.request.LogoutRequest;

import java.util.logging.Logger;

@Handler
public class LogoutHandler implements RequestHandler<LogoutRequest, LogoutResponse> {
    private static final Logger LOGGER= Logger.getLogger(LogoutHandler.class.getName());
    @Override
    public LogoutResponse handleRequest(LogoutRequest request) {
        UserSessionContext.clear();
        return new LogoutResponse();
    }
}
