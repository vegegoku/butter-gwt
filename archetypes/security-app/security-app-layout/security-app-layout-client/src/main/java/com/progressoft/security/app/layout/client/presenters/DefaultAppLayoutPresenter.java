package com.progressoft.security.app.layout.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.app.layout.client.views.LayoutView;
import org.akab.engine.core.api.shared.extension.MainContext;

@Presenter
public class DefaultAppLayoutPresenter extends BaseClientPresenter<LayoutView> implements AppLayoutPresenter {

    private AuthenticationCompletedContext authenticationCompletedContext;
    private MainContext mainContext;

    @Override
    public void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context) {
        this.authenticationCompletedContext=context;
        mainContext.appendWidgetToRoot(view);
    }

    @Override
    public void onMainContextRecieved(MainContext context) {
        this.mainContext=context;
    }
}