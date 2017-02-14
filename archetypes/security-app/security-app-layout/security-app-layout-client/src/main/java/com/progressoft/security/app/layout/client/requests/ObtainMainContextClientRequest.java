package com.progressoft.security.app.layout.client.requests;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainMainContextClientRequest extends ClientRequest<AppLayoutPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainMainContextClientRequest.class);

    private final MainContext context;

    public ObtainMainContextClientRequest(MainContext context) {
        this.context = context;
    }

    @Override
    protected void process(AppLayoutPresenter presenter) {
        presenter.onMainContextRecieved(context);
    }
}