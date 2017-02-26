package com.progressoft.security.logout.client.requests;

import com.progressoft.security.logout.client.presenters.LogoutPresenter;
import com.progressoft.security.logout.shared.request.LogoutRequest;
import com.progressoft.security.logout.shared.response.LogoutResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class LogoutServerRequest extends ClientServerRequest<LogoutPresenter, LogoutRequest, LogoutResponse> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(LogoutServerRequest.class);

    @Override
    protected void process(LogoutPresenter presenter, LogoutRequest serverRequest, LogoutResponse response) {
        presenter.onLoggedOut();
    }

    @Override
    public LogoutRequest buildArguments() {
        return new LogoutRequest();
    }
}
