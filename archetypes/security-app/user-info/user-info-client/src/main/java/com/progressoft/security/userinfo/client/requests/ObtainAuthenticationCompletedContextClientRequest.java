package com.progressoft.security.userinfo.client.requests;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainAuthenticationCompletedContextClientRequest extends ClientRequest<UserInfoPresenter> {

    private static final CoreLogger LOGGER =
            CoreLoggerFactory.getLogger(ObtainAuthenticationCompletedContextClientRequest.class);
    private final AuthenticationCompletedContext context;

    public ObtainAuthenticationCompletedContextClientRequest(AuthenticationCompletedContext context) {
        this.context = context;
    }

    @Override
    protected void process(UserInfoPresenter presenter) {
        presenter.onAuthenticationCompletedContextReceived(context);
    }
}