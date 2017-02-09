package com.progressoft.security.otp.client.requests;

import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainAuthenticationLayoutClientRequest extends ClientRequest<OtpPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainAuthenticationLayoutClientRequest.class);

    private final AuthenticationLayoutContext context;

    public ObtainAuthenticationLayoutClientRequest(AuthenticationLayoutContext context) {
        this.context = context;
    }

    @Override
    protected void process(OtpPresenter presenter) {
        presenter.onAuthenticationLayoutInitialized(context);
    }
}