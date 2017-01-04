package com.progressoft.security.sample.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface SamplePresenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage);

    void setResponse(String message);
}