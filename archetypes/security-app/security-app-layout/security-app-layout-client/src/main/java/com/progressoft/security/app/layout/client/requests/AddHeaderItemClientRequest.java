package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class AddHeaderItemClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AddHeaderItemClientRequest.class);
    private final LayoutItem headerItem;

    public AddHeaderItemClientRequest(LayoutItem headerItem) {
        this.headerItem = headerItem;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onAddHeaderItem(headerItem);
    }
}