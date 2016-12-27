package com.progressoft.security.login.client.presenters;

import com.google.gwt.user.client.Window;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.login.client.views.LoginView;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultLoginPresenter extends BaseClientPresenter<LoginView> implements LoginPresenter {

    public DefaultLoginPresenter() {
    }

    @Override
    public void initView(LoginView view) {

    }

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        view.setWelcomeMessage(welcomeMessage);
        mainExtensionPoint.context().appendWidgetToRoot(view);
    }
}