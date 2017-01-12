package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;

import com.google.gwt.user.client.ui.HasText;
import com.progressoft.security.login.client.presenters.LoginCredentials;
import com.progressoft.security.login.client.presenters.LoginHandler;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.login.client.presenters.LoginPresenter;

@UiView(presentable = LoginPresenter.class)
public class DefaultLoginView extends Composite implements LoginView{

    private LoginHandler loginHandler;


    interface DefaultLoginViewUiBinder extends UiBinder<MaterialPanel, DefaultLoginView> {
    }

    private static DefaultLoginViewUiBinder ourUiBinder = GWT.create(DefaultLoginViewUiBinder.class);

    @UiField
    MaterialPanel mainPanel;

    @UiField
    MaterialTextBox userName;

    @UiField
    MaterialTextBox password;

    @UiField
    MaterialTextBox tenant;

    @UiField
    MaterialButton loginButton;

    public DefaultLoginView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void show(String defaultTenant){
        userName.setText("");
        password.setText("");
        tenant.setText(defaultTenant);
    }

    @UiHandler("loginButton")
    void onLoginButtonClick(ClickEvent event){
        this.loginHandler.handle(makeLoginCredentials());
    }

    protected LoginCredentials makeLoginCredentials() {
        return new LoginCredentials() {
            @Override
            public String getUserName() {
                return userName.getText();
            }

            @Override
            public String getPassword() {
                return password.getText();
            }

            @Override
            public String getTenant() {
                return tenant.getText();
            }
        };
    }

    protected String getUserName(){
        return userName.getText();
    }

    protected String getPassword(){
        return password.getText();
    }
    protected String getTenant() {
        return tenant.getText();
    }

    protected void setUserName(String userName) {
        this.userName.setText(userName);
    }

    protected void setPassword(String password){
        this.password.setText(password);
    }
    protected void setTenant(String tenant){
        this.tenant.setText(tenant);
    }

    @Override
    public void addLoginHandler(LoginHandler loginHandler) {
        this.loginHandler=loginHandler;
    }

    @Override
    public void invalidateUserName(String errorMessage) {
        this.userName.setError(errorMessage);
    }

    @Override
    public void invalidatePassword(String errorMessage) {
        this.password.setError(errorMessage);
    }

    @Override
    public void invalidateTenant(String errorMessage) {
        this.tenant.setError(errorMessage);
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
}
