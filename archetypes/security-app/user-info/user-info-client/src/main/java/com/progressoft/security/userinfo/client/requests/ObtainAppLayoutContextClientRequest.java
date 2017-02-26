package com.progressoft.security.userinfo.client.requests;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class ObtainAppLayoutContextClientRequest extends ClientRequest<UserInfoPresenter> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(ObtainAppLayoutContextClientRequest.class);
    private final AppLayoutContext context;

    public ObtainAppLayoutContextClientRequest(AppLayoutContext context) {
        this.context = context;
    }

    @Override
    protected void process(UserInfoPresenter presenter) {
        presenter.onAppLayoutContextRecieved(context);
    }
}