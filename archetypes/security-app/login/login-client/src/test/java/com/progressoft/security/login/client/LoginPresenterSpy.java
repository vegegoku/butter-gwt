package com.progressoft.security.login.client;

import com.progressoft.security.login.client.presenters.DefaultLoginPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class LoginPresenterSpy extends DefaultLoginPresenter{

    private MainExtensionPoint mainExtensionPoint;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint) {
        super.contributeToMainModule(mainExtensionPoint);
        this.mainExtensionPoint=mainExtensionPoint;
    }

    public MainExtensionPoint getMainExtensionPoint() {
        return mainExtensionPoint;
    }

    @Override
    protected String getConcrete() {
        return DefaultLoginPresenter.class.getCanonicalName();
    }
}
