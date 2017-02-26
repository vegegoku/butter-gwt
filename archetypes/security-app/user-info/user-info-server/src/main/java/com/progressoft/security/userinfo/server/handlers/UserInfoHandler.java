package com.progressoft.security.userinfo.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.userinfo.shared.response.UserInfoResponse;
import com.progressoft.security.userinfo.shared.request.UserInfoRequest;

import java.util.logging.Logger;

@Handler
public class UserInfoHandler implements RequestHandler<UserInfoRequest, UserInfoResponse> {
    private static final Logger LOGGER= Logger.getLogger(UserInfoHandler.class.getName());
    @Override
    public UserInfoResponse handleRequest(UserInfoRequest request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new UserInfoResponse("Server message");
    }
}
