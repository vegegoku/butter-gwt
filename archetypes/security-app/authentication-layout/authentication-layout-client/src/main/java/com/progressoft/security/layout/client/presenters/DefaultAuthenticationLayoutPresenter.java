package com.progressoft.security.layout.client.presenters;

import com.google.gwt.user.client.ui.IsWidget;
import com.progressoft.security.layout.client.views.AuthenticationLayoutView;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultAuthenticationLayoutPresenter extends BaseClientPresenter<AuthenticationLayoutView>
        implements AuthenticationLayoutPresenter {

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint) {
        mainExtensionPoint.context().appendWidgetToRoot(view);
        Contributions.apply(AuthenticationLayoutExtensionPoint.class,
                (AuthenticationLayoutExtensionPoint) () -> new AuthenticationLayoutContext() {
                    @Override
                    public void showViewInMainPanel(IsWidget v) {
                        view.showView(v);
                    }

                    @Override
                    public void hideViewFromMainPanel(IsWidget v) {
                        view.hideView(v);
                    }
                });
    }


}