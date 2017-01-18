package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.login.client.presenters.Conclusion;
import com.progressoft.security.login.client.presenters.LoginHandler;
import com.progressoft.security.login.client.presenters.LoginPresenter;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import com.progressoft.security.login.shared.extension.ViewLoginCredentials;
import gwt.material.design.client.ui.*;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = LoginPresenter.class)
public class DefaultLoginView extends Composite implements LoginView {

    private LoginHandler loginHandler;


    interface DefaultLoginViewUiBinder extends UiBinder<MaterialRow, DefaultLoginView> {
    }

    private static DefaultLoginViewUiBinder ourUiBinder = GWT.create(DefaultLoginViewUiBinder.class);

    @UiField
    MaterialTextBox userName;

    @UiField
    MaterialTextBox password;

    @UiField
    MaterialTextBox tenant;

    @UiField
    MaterialButton loginButton;

    @UiField
    MaterialModal errorDialog;

    @UiField
    MaterialTitle errorDialogTitle;

    public DefaultLoginView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public LoginView show(String defaultTenant) {
        userName.setText("");
        password.setText("");
        tenant.setText(defaultTenant);
        return this;
    }

    @UiHandler("loginButton")
    void onLoginButtonClick(ClickEvent event) {
        MaterialLoader.showProgress(true);
        this.loginHandler.handle(makeLoginCredentials());
    }

    protected LoginCredentials makeLoginCredentials() {
        return new ViewLoginCredentials(userName.getText(), password.getText(), tenant.getText());
    }

    protected String getUserName() {
        return userName.getText();
    }

    protected String getPassword() {
        return password.getText();
    }

    protected String getTenant() {
        return tenant.getText();
    }

    protected void setUserName(String userName) {
        this.userName.setText(userName);
    }

    protected void setPassword(String password) {
        this.password.setText(password);
    }

    protected void setTenant(String tenant) {
        this.tenant.setText(tenant);
    }

    @Override
    public void addLoginHandler(LoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }

    private void invalidateUserName(String errorMessage) {
        this.userName.setError(errorMessage);
    }

    private void invalidatePassword(String errorMessage) {
        this.password.setError(errorMessage);
    }

    private void invalidateTenant(String errorMessage) {
        this.tenant.setError(errorMessage);
    }

    @Override
    public void invalidateFields(Conclusion conclusion) {
        conclusion.invalidateFiled(Bundle.USERNAME, this::invalidateUserName);
        conclusion.invalidateFiled(Bundle.SECRET, this::invalidatePassword);
        conclusion.invalidateFiled(Bundle.TENANT, this::invalidateTenant);
//        MaterialLoader.showProgress(false);
    }

    protected MaterialTextBox getUserNameField() {
        return this.userName;
    }

    protected MaterialTextBox getPasswordField() {
        return password;
    }

    protected MaterialTextBox getTenantField() {
        return tenant;
    }

    @Override
    public void showErrorMessage(String errorMessage) {

//        MaterialLoader.showProgress(false);
        errorDialogTitle.setDescription(errorMessage);
        errorDialog.open();
    }
}
