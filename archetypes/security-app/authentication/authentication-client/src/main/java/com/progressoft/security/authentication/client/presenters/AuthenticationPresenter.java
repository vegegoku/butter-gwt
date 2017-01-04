package com.progressoft.security.authentication.client.presenters;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface AuthenticationPresenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage);

    void onAuthenticationCompleted(Principal principal);

    void applyAuthenticationContributions();
}