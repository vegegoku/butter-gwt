package com.progressoft.security.layout.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface AuthenticationLayoutPresenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint);
}