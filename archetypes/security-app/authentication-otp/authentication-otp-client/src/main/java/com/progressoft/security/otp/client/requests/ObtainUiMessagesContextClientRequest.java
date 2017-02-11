package com.progressoft.security.otp.client.requests;

import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainUiMessagesContextClientRequest extends ClientRequest<OtpPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainUiMessagesContextClientRequest.class);

    private final UiMessagesContext uiMessagesContext;

    public ObtainUiMessagesContextClientRequest(UiMessagesContext uiMessagesContext) {
        this.uiMessagesContext = uiMessagesContext;
    }

    @Override
    protected void process(OtpPresenter presenter) {
        presenter.onUiMessagesContextRecieved(uiMessagesContext);
    }
}