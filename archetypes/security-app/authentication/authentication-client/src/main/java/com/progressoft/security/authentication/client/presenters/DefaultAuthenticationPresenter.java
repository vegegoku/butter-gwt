package com.progressoft.security.authentication.client.presenters;

import com.google.gwt.user.client.Window;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationExtensionPoint;
import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.CompleteAuthenticationOnServerRequest;
import com.progressoft.security.authentication.client.requests.FindRootAuthenticationChainRequest;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.shared.extension.*;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.authentication.client.views.AuthenticationView;

import java.util.Objects;

@Presenter
public class DefaultAuthenticationPresenter extends BaseClientPresenter<AuthenticationView>
        implements AuthenticationPresenter {

    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    private final AuthenticationContext authenticationContext = new DefaultAuthenticationContext();
    private Principal principal;

    @Override
    public void onAuthenticationCompleted(Principal principal) {
        Window.alert("onAuthenticationCompleted");
        Contributions.apply(AuthenticationCompletedExtensionPoint.class, makeAuthenticationExtensionPoint(principal));
    }

    @Override
    public void applyAuthenticationContributions() {
        Contributions.apply(AuthenticationExtensionPoint.class, new DefaultAuthenticationExtensionPoint(authenticationContext));
        authenticate();
    }

    @Override
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
        if(Objects.isNull(principal))
            principal=context.getPrincipal();
        toNextChain(principal);
    }

    private void toNextChain(Principal principal) {
        if(principal.chains().isEmpty())
            completeAuthenticationOnServer(principal);
        else
            new ChainedAuthentication(this, principal.chains()).fireNextChain();
    }

    private void completeAuthenticationOnServer(Principal principal) {
        new CompleteAuthenticationOnServerRequest(principal).send();
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        authenticate();
    }

    @Override
    public void showErrorMessage() {
        view.showErrorMessage(AUTHENTICATION_FAILED);
    }
}