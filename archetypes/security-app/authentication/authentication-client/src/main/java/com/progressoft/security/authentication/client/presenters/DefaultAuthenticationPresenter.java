package com.progressoft.security.authentication.client.presenters;

import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationExtensionPoint;
import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.CompleteAuthenticationOnServer;
import com.progressoft.security.authentication.client.requests.FindRootAuthenticationChainRequest;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.shared.extension.*;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.authentication.client.views.AuthenticationView;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultAuthenticationPresenter extends BaseClientPresenter<AuthenticationView>
        implements AuthenticationPresenter {

    private final AuthenticationContext authenticationContext=new DefaultAuthenticationContext();

    @Override
    public void initView(AuthenticationView view) {
    }

    @Override
    public void onAuthenticationCompleted(Principal principal) {
        Contributions.apply(AuthenticationCompletedExtensionPoint.class, makeAuthenticationExtensionPoint(principal));
    }

    @Override
    public void applyAuthenticationContributions() {
        Contributions.apply(AuthenticationExtensionPoint.class,
                new DefaultAuthenticationExtensionPoint(authenticationContext));
        authenticate();
    }

    public void authenticate() {
        new UserLoggedInRequest().send();
    }

    private DefaultAuthenticationCompletedExtensionPoint makeAuthenticationExtensionPoint(Principal principal) {
        return new DefaultAuthenticationCompletedExtensionPoint(new DefaultAuthenticationCompletedContext(principal));
    }

    @Override
    public void startAuthentication(String rootChain) {
        AuthenticationProviderRegistry.get(rootChain).begin();
    }

    @Override
    public void onNoAuthenticatedUserFound() {
        new FindRootAuthenticationChainRequest().send();
    }

    @Override
    public void onChainCompletedSuccessfully(CompletedChainContext context) {
        new CompleteAuthenticationOnServer(context.getPrincipal()).send();
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        authenticate();
    }

    @Override
    public void onAuthenticationCompletionError() {
        view.showErrorMessage("Failed to complete authentication on server.!");
    }
}