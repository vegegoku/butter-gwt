package com.progressoft.security.logout.client.requests;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.logout.client.presenters.LogoutPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainAppLayoutClientRequest extends ClientRequest<LogoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainAppLayoutClientRequest.class);
    private final AppLayoutContext context;

    public ObtainAppLayoutClientRequest(AppLayoutContext context) {
        this.context = context;
    }

    @Override
    protected void process(LogoutPresenter presenter) {
        presenter.onAppLayoutContextReceived(context);
    }
}