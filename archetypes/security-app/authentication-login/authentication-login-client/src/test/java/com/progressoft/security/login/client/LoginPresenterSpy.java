package com.progressoft.security.login.client;

import com.progressoft.security.login.client.presenters.DefaultLoginPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class LoginPresenterSpy extends DefaultLoginPresenter{

    private boolean contributionCompleted;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        super.contributeToMainModule(mainExtensionPoint, welcomeMessage);
        this.contributionCompleted=true;
    }

    public boolean isContributionCompleted() {
        return contributionCompleted;
    }

    @Override
    protected String getConcrete() {
        return DefaultLoginPresenter.class.getCanonicalName();
    }
}
