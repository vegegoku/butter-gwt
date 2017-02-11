package com.progressoft.security.login.client.requests;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainUiMessagesContextClientRequest extends ClientRequest<LoginPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainUiMessagesContextClientRequest.class);

    private final UiMessagesContext uiMessagesContext;

    public ObtainUiMessagesContextClientRequest(UiMessagesContext context) {
        uiMessagesContext = context;
    }

    @Override
    protected void process(LoginPresenter presenter) {
        presenter.onUiMessagesContextRecieved(uiMessagesContext);
    }
}