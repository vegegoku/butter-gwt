package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.client.presenters.DefaultAuthenticationPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class AuthenticationPresenterSpy extends DefaultAuthenticationPresenter {

    private boolean contributionCompleted;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        super.contributeToMainModule(mainExtensionPoint, welcomeMessage);
        this.contributionCompleted = true;
    }

    public boolean isContributionCompleted() {
        return contributionCompleted;
    }

    @Override
    protected String getConcrete() {
        return DefaultAuthenticationPresenter.class.getCanonicalName();
    }

}
