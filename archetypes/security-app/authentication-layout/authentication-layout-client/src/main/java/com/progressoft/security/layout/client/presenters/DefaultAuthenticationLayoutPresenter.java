package com.progressoft.security.layout.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedContext;
import com.progressoft.security.layout.client.views.AuthenticationLayoutView;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutExtensionPoint;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import org.akab.engine.core.api.shared.extension.MainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultAuthenticationLayoutPresenter extends BaseClientPresenter<AuthenticationLayoutView>
        implements AuthenticationLayoutPresenter {

    private MainContext mainContext;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint) {
        this.mainContext=mainExtensionPoint.context();
        mainExtensionPoint.context().appendWidgetToRoot(view);
        Contributions.apply(AuthenticationLayoutExtensionPoint.class,
                (AuthenticationLayoutExtensionPoint) () -> (AuthenticationLayoutContext) v -> view.showView(v));
    }

    @Override
    public void onAuthenticationCompletedContextRecieved(AuthenticationCompletedContext context) {
        this.mainContext.removeWidget(view);
    }
}