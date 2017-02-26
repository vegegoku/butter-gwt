package com.progressoft.security.logout.client.presenters;

import com.progressoft.security.app.layout.shared.extension.AppLayoutContext;
import com.progressoft.security.logout.client.requests.LogoutServerRequest;
import com.progressoft.security.logout.client.views.CompositeLogoutView;
import com.progressoft.security.logout.client.views.LogoutView;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.MultiViewBaseClientPresenter;

@Presenter
public class DefaultLogoutPresenter extends MultiViewBaseClientPresenter<CompositeLogoutView> implements LogoutPresenter {

    private AppLayoutContext appLayoutContext;

    @Override
    public void initView(CompositeLogoutView view) {
        view.setLogoutHandler(() -> new LogoutServerRequest().send());
    }

    @Override
    public void onLoggedOut() {
        view.onLoggedOut();
    }

    @Override
    public void onAppLayoutContextReceived(AppLayoutContext context) {
        this.appLayoutContext=context;
        this.appLayoutContext.addHeaderItem(view.getView(LogoutView.HEADER));
        this.appLayoutContext.addMenuItem(view.getView(LogoutView.MENU));
    }
}