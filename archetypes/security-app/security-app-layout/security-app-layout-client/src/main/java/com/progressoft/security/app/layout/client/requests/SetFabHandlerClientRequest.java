package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class SetFabHandlerClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(SetFabHandlerClientRequest.class);
    private final FabHandler fabHandler;

    public SetFabHandlerClientRequest(FabHandler fabHandler) {
        this.fabHandler = fabHandler;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onSetFabHandler(fabHandler);
    }
}