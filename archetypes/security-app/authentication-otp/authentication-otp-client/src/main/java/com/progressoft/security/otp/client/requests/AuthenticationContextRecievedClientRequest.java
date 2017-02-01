package com.progressoft.security.otp.client.requests;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class AuthenticationContextRecievedClientRequest extends ClientRequest<OtpPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AuthenticationContextRecievedClientRequest.class);
    private final AuthenticationContext context;

    public AuthenticationContextRecievedClientRequest(AuthenticationContext context) {
        this.context = context;
    }

    @Override
    protected void process(OtpPresenter presenter) {
        presenter.onAuthenticationContextRecieved(context);
    }
}