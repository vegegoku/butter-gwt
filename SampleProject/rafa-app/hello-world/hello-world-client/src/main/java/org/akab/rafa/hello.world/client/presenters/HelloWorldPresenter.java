package org.akab.rafa.hello.world.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface HelloWorldPresenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage);
}