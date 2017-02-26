package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class SetSideContentClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(SetSideContentClientRequest.class);
    private final LayoutItem content;

    public SetSideContentClientRequest(LayoutItem content) {
        this.content = content;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onSetSideContent(content);
    }
}