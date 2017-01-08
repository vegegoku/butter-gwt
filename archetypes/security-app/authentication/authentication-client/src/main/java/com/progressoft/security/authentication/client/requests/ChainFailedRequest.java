package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.extension.FailedChainContext;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;

@Request
public class ChainFailedRequest extends ClientRequest<AuthenticationPresenter>{

    private final FailedChainContext failedChainContext;

    public ChainFailedRequest(FailedChainContext failedChainContext) {
        this.failedChainContext = failedChainContext;
    }

    @Override
    protected void process(AuthenticationPresenter presenter) {
        presenter.onChainFailed(failedChainContext);
    }
}
