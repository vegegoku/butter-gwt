package com.progressoft.security.authentication.client.presenters;

import com.google.gwt.user.client.Window;
import com.progressoft.security.authentication.client.extensions.*;
import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;
import com.progressoft.security.authentication.client.requests.CompleteAuthenticationOnServerRequest;
import com.progressoft.security.authentication.client.requests.FindRootAuthenticationChainRequest;
import com.progressoft.security.authentication.client.requests.UserLoggedInRequest;
import com.progressoft.security.authentication.shared.extension.*;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.authentication.client.views.AuthenticationView;


import static java.util.Objects.*;

@Presenter
public class DefaultAuthenticationPresenter extends BaseClientPresenter<AuthenticationView>
        implements AuthenticationPresenter {

    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    private final AuthenticationContext authenticationContext = new DefaultAuthenticationContext();
    private RootChainContext rootChainContext;
    private UiMessagesContext uiMessagesContext;

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
        if (isNull(rootChainContext))
            applyRootChainContributions(context);
        toNextChain(rootChainContext.principal());
    }

    private void applyRootChainContributions(CompletedChainContext context) {
        rootChainContext = new DefaultRootChainContext(context.getPrincipal());
        Contributions.apply(RootChainCompletedExtensionPoint.class, new DefaultRootChainExtensionPoint(rootChainContext));
    }

    private void toNextChain(Principal principal) {
        if (principal.chains().isEmpty())
            completeAuthenticationOnServer(principal);
        else
            new ChainedAuthentication(this, principal.chains()).fireNextChain();
    }

    private void completeAuthenticationOnServer(Principal principal) {
        new CompleteAuthenticationOnServerRequest(principal).send();
    }

    @Override
    public void onUiMessagesContextRecieved(UiMessagesContext context) {
        this.uiMessagesContext=context;
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        rootChainContext=null;
        authenticate();
    }

    @Override
    public void showErrorMessage() {
        uiMessagesContext.showError(AUTHENTICATION_FAILED, "Could not authentication user.");
    }
}