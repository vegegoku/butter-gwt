package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainUiMessagesContextClientRequest extends ClientRequest<AuthenticationPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainUiMessagesContextClientRequest.class);

    private final UiMessagesContext context;

    public ObtainUiMessagesContextClientRequest(UiMessagesContext context) {
        this.context = context;
    }

    @Override
    protected void process(AuthenticationPresenter presenter) {
        presenter.onUiMessagesContextRecieved(context);
    }
}