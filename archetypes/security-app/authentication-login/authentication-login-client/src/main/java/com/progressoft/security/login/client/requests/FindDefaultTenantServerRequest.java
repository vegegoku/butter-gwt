package com.progressoft.security.login.client.requests;

import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.shared.request.DefaultTenantRequest;
import com.progressoft.security.login.shared.response.DefaultTenantResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class FindDefaultTenantServerRequest
        extends ClientServerRequest<LoginPresenter, DefaultTenantRequest, DefaultTenantResponse> {

    @Override
    protected void process(LoginPresenter presenter, DefaultTenantRequest serverRequest,
                           DefaultTenantResponse response) {
        presenter.showLoginDialog(response.getDefaultTenant());
    }

    @Override
    public DefaultTenantRequest buildArguments() {
        return new DefaultTenantRequest();
    }
}
