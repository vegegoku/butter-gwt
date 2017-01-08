package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.extension.CompletedChainContext;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;

@Request
public class ChainCompletedSuccessfullyRequest extends ClientRequest<AuthenticationPresenter>{

    private final CompletedChainContext context;

    public ChainCompletedSuccessfullyRequest(CompletedChainContext context) {
        this.context = context;
    }

    @Override
    protected void process(AuthenticationPresenter presenter) {
        presenter.onChainCompletedSuccessfully(context);
    }
}
