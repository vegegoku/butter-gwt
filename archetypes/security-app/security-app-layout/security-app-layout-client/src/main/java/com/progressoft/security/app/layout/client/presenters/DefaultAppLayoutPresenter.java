package com.progressoft.security.app.layout.client.presenters;

import com.google.gwt.user.client.Window;
import com.progressoft.security.app.layout.client.views.LayoutView;
import com.progressoft.security.app.layout.shared.extension.AppLayoutExtensionPoint;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import org.akab.engine.core.api.shared.extension.MainContext;


@Presenter
public class DefaultAppLayoutPresenter extends BaseClientPresenter<LayoutView> implements AppLayoutPresenter {

    private MainContext mainContext;

    @Override
    public void onShowContent(LayoutItem content) {
        view.showContent(content);
    }

    @Override
    public void onShowRightPanel() {
       view.showRightPanel();
    }

    @Override
    public void onHideRightPanel() {
        view.hideRightPanel();
    }

    @Override
    public void onSetFabHandler(FabHandler fabHandler) {
        view.setFabHandler(fabHandler);
    }

    @Override
    public void onAddFabItem(LayoutItem fabItem) {
        view.addFabItem(fabItem);
    }

    @Override
    public void onSetSideContent(LayoutItem content) {
        view.setSideContent(content);
    }

    @Override
    public void onAddMenuItem(LayoutItem menuItem, int beforeIndex) {
        view.addMenuItem(menuItem, beforeIndex);
    }

    @Override
    public void onAddHeaderItem(LayoutItem headerItem) {
        view.addHeaderItem(headerItem);
    }

    @Override
    public void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context) {
        mainContext.appendWidgetToRoot(view);
        Contributions.apply(AppLayoutExtensionPoint.class,
                (AppLayoutExtensionPoint) DefaultAppLayoutContext::new);
    }

    @Override
    public void onMainContextRecieved(MainContext context) {
        this.mainContext=context;
    }
}