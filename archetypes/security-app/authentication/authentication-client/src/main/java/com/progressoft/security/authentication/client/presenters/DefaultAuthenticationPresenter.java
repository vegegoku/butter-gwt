package com.progressoft.security.authentication.client.presenters;

import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationContext;
import com.progressoft.security.authentication.client.extensions.DefaultAuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.AuthenticationExtensionPoint;
import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.extension.Contributions;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.authentication.client.views.AuthenticationView;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultAuthenticationPresenter extends BaseClientPresenter<AuthenticationView>
        implements AuthenticationPresenter {

    @Override
    public void initView(AuthenticationView view) {
        AuthenticationProviderRegistryHolder.registry
    }

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        view.setWelcomeMessage(welcomeMessage);
        mainExtensionPoint.context().appendWidgetToRoot(view);
    }

    @Override
    public void onAuthenticationCompleted(Principal principal) {
        Contributions
                .apply(AuthenticationCompletedExtensionPoint.class, makeAuthenticationExtensionPoint(principal));
    }

    @Override
    public void applyAuthenticationContributions() {
        Contributions.apply(AuthenticationExtensionPoint.class,
                new DefaultAuthenticationExtensionPoint(new DefaultAuthenticationContext()));
    }

    private DefaultAuthenticationCompletedExtensionPoint makeAuthenticationExtensionPoint(Principal principal) {
        return new DefaultAuthenticationCompletedExtensionPoint(new DefaultAuthenticationCompletedContext(principal));
    }
}