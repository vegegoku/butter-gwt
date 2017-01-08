package com.progressoft.security.authentication.client.presenters;

import com.progressoft.security.authentication.shared.extension.CompletedChainContext;
import com.progressoft.security.authentication.shared.extension.FailedChainContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface AuthenticationPresenter extends Presentable{

    void onAuthenticationCompleted(Principal principal);

    void applyAuthenticationContributions();

    void startAuthentication(String rootChain);

    void onNoAuthenticatedUserFound();

    void onChainCompletedSuccessfully(CompletedChainContext context);

    void onChainFailed(FailedChainContext failedChainContext);

    void authenticate();

    void onAuthenticationCompletionError();
}