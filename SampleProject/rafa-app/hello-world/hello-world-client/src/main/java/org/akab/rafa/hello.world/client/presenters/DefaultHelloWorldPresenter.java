package org.akab.rafa.hello.world.client.presenters;

import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.rafa.hello.world.client.views.HelloWorldView;

@Presenter(presentable = HelloWorldPresenter.class)
public class DefaultHelloWorldPresenter extends BaseClientPresenter<HelloWorldView> implements HelloWorldPresenter {

    @Override
    public void initView(HelloWorldView view) {

    }

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        view.setWelcomeMessage(welcomeMessage);
        mainExtensionPoint.context().appendWidgetToRoot(view);
    }
}