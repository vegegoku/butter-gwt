package com.progressoft.security.app.layout.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class SetFabHandlerClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(SetFabHandlerClientRequest.class);

    @Override
    protected void process(AppLayoutPresenter presenter) {

    }
}