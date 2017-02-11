package com.progressoft.security.uimessages.client.requests;

import com.progressoft.security.uimessages.client.presenters.UiMessagesPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ShowErrorMessageClientRequest extends ClientRequest<UiMessagesPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ShowErrorMessageClientRequest.class);
    private final String message;
    private final String details;

    public ShowErrorMessageClientRequest(String message, String details) {
        this.message = message;
        this.details = details;
    }

    @Override
    protected void process(UiMessagesPresenter presenter) {
        presenter.onShowErrorMessage(message, details);
    }
}