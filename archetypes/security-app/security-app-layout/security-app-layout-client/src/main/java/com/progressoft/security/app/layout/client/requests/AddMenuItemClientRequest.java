package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class AddMenuItemClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(AddMenuItemClientRequest.class);
    private final LayoutItem menuItem;

    public AddMenuItemClientRequest(LayoutItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onAddMenuItem(menuItem);
    }
}