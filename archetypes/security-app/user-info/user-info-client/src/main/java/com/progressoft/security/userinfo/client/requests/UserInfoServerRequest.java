package com.progressoft.security.userinfo.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.progressoft.security.userinfo.shared.response.UserInfoResponse;
import com.progressoft.security.userinfo.shared.request.UserInfoRequest;
import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.annotations.Request;

@Request
public class UserInfoServerRequest extends ClientServerRequest<UserInfoPresenter, UserInfoRequest, UserInfoResponse> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(UserInfoServerRequest.class);

    @Override
    protected void process(UserInfoPresenter presenter, UserInfoRequest serverRequest, UserInfoResponse response) {
        LOGGER.info("Message recieved from server : "+response.getServerMessage());
    }

    @Override
    public UserInfoRequest buildArguments() {
        return new UserInfoRequest("client message");
    }
}
