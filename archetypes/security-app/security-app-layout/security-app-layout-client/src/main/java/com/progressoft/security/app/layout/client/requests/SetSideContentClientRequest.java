package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ShowSideContentClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ShowSideContentClientRequest.class);
    private final LayoutItem content;

    public ShowSideContentClientRequest(LayoutItem content) {
        this.content = content;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onShowSideContent(content);
    }
}