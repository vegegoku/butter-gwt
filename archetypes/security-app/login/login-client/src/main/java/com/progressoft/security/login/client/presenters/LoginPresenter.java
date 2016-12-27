package com.progressoft.security.login.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface LoginPresenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage);
}