package com.progressoft.security.login.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.login.client.provider.LoginClientAuthenticationProvider;
import com.progressoft.security.login.client.requests.LoginServerRequest;
import com.progressoft.security.login.client.views.Bundle;
import com.progressoft.security.login.client.views.LoginView;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Presenter
public class DefaultLoginPresenter extends BaseClientPresenter<LoginView> implements LoginPresenter {

    private static final String REQUIRED = "Required";
    private AuthenticationContext authenticationContext;
    private AuthenticationLayoutContext authenticationLayoutContext;

    @FunctionalInterface
    private interface FieldValidation {
        void invalidate(LoginCredentials credentials, Conclusion conclusion);
    }

    private FieldValidation usernameValidation = (credentials, conclusion) -> {
        if (isEmptyField(credentials.getUserName()))
            conclusion.raiseError(Bundle.USERNAME, REQUIRED);
    };

    private FieldValidation passwordValidation = (credentials, conclusion) -> {
        if (isEmptyField(credentials.getPassword()))
            conclusion.raiseError(Bundle.SECRET, REQUIRED);
    };

    private FieldValidation tenantValidation = (credentials, conclusion) -> {
        if (isEmptyField(credentials.getTenant()))
            conclusion.raiseError(Bundle.TENANT, REQUIRED);
    };

    private List<FieldValidation> validations =
            Arrays.asList(usernameValidation, passwordValidation, tenantValidation);

    @Override
    public void initView(LoginView view) {
        view.addLoginHandler(loginCredentials -> {
            Conclusion conclusion = validateLoginCredentials(loginCredentials);
            if (conclusion.hasErrors())
                view.invalidateFields(conclusion);
            else
                new LoginServerRequest(loginCredentials).send();
        });
    }

    private boolean isEmptyField(String value) {
        return isNull(value) || value.trim().isEmpty();
    }

    private Conclusion validateLoginCredentials(LoginCredentials loginCredentials) {
        Conclusion conclusion = new FieldsConclusion();
        validations.forEach(validation -> validation.invalidate(loginCredentials, conclusion));
        return conclusion;
    }

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        this.authenticationContext=context;
        context.registerProvider(LoginClientAuthenticationProvider.LOGIN, new LoginClientAuthenticationProvider());
    }

    @Override
    public void showLoginDialog(String defaultTenant) {
        authenticationLayoutContext.showViewInMainPanel(view.show(defaultTenant));
    }

    @Override
    public void onLoginSuccess(Principal principal) {
        authenticationContext.onChainCompleted(() -> principal);
        authenticationLayoutContext.hideViewFromMainPanel(view);
    }

    @Override
    public void onLayoutContextReceived(AuthenticationLayoutContext context) {
        this.authenticationLayoutContext = context;
    }

    @Override
    public void showError() {
        view.showErrorMessage("Bad credentials");
    }

    private static class FieldsConclusion implements Conclusion {

        Map<String, String> errors = new HashMap<>();

        @Override
        public void raiseError(String fieldName, String errorMessage) {
            this.errors.put(fieldName, errorMessage);
        }

        @Override
        public boolean hasErrors() {
            return !errors.isEmpty();
        }

        @Override
        public void invalidateFiled(String name, InvalidationStrategy strategy) {
            if(errors.containsKey(name))
                strategy.invalidate(errors.get(name));
        }
    }
}