package com.progressoft.security.otp.client.requests;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainPrincipalFromRootChainClientRequest extends ClientRequest<OtpPresenter> {

    private static final CoreLogger LOGGER =
            CoreLoggerFactory.getLogger(ObtainPrincipalFromRootChainClientRequest.class);

    public ObtainPrincipalFromRootChainClientRequest(
            Principal principal) {

    }

    @Override
    protected void process(OtpPresenter presenter) {

    }
}