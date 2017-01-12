package com.progressoft.security.authentication.client.requests;

import com.progressoft.security.authentication.client.presenters.AuthenticationPresenter;
import com.progressoft.security.authentication.shared.request.FindRootChainRequest;
import com.progressoft.security.authentication.shared.response.FindRootChainResponse;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.api.shared.request.FailedServerResponse;

@Request
public class FindRootAuthenticationChainRequest extends
        ClientServerRequest<AuthenticationPresenter, FindRootChainRequest, FindRootChainResponse> {
    @Override
    public void processFailed(AuthenticationPresenter presenter, FindRootChainRequest serverArgs,
                                 FailedServerResponse failedResponse) {
        presenter.showErrorMessage();
    }

    @Override
    protected void process(AuthenticationPresenter presenter, FindRootChainRequest serverArgs,
                           FindRootChainResponse response) {
        presenter.startAuthentication(response.getRootChain());
    }

    @Override
    public FindRootChainRequest buildArguments() {
        return new FindRootChainRequest();
    }
}
