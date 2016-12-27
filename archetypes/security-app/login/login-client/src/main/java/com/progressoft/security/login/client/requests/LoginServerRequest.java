package com.progressoft.security.login.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.progressoft.security.login.shared.response.LoginResponse;
import com.progressoft.security.login.shared.request.LoginArgs;
import com.progressoft.security.login.client.presenters.LoginPresenter;

public class LoginServerRequest extends ClientServerRequest<LoginPresenter, LoginArgs, LoginResponse> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(LoginServerRequest.class);

    @Override
    protected void process(LoginPresenter presenter, LoginArgs serverArgs, LoginResponse response) {
        LOGGER.info("Message recieved from server : "+response.getServerMessage());
    }

    @Override
    public LoginArgs buildArguments() {
        return new LoginArgs("client message");
    }
}
