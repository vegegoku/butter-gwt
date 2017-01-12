package com.progressoft.security.login.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.login.client.provider.LoginClientAuthenticationProvider;
import com.progressoft.security.login.client.views.LoginView;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;

import static java.util.Objects.isNull;

@Presenter
public class DefaultLoginPresenter extends BaseClientPresenter<LoginView> implements LoginPresenter {

    public static final String REQUIRED = "Required";

    @Override
    public void initView(LoginView view) {
        view.addLoginHandler(loginCredentials -> {
            if(isNull(loginCredentials.getUserName()) || loginCredentials.getUserName().trim().isEmpty())
                view.invalidateUserName(REQUIRED);
            if(isNull(loginCredentials.getPassword()) || loginCredentials.getPassword().trim().isEmpty())
                view.invalidatePassword(REQUIRED);
            if(isNull(loginCredentials.getTenant()) || loginCredentials.getTenant().trim().isEmpty())
                view.invalidateTenant(REQUIRED);

            // TODO: 1/13/17 next we should write the actual login authentication on server.

        });
    }

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        context.registerProvider(LoginClientAuthenticationProvider.LOGIN, new LoginClientAuthenticationProvider());
    }

    @Override
    public void showLoginDialog(String defaultTenant) {
        view.show(defaultTenant);
    }
}