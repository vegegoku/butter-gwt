package com.progressoft.security.login.client;

import com.progressoft.security.login.client.presenters.LoginHandler;
import com.progressoft.security.login.client.views.DefaultLoginView;
import com.progressoft.security.login.client.views.LoginView;
import gwt.material.design.client.ui.MaterialTextBox;

public class LoginViewSpy extends DefaultLoginView {

    private boolean loginDialogVisible=false;
    private LoginHandler loginHandler;
    private String errorMessage;

    public boolean isLoginDialogVisible() {
        return loginDialogVisible;
    }

    @Override
    public LoginView show(String defaultTenant) {
        super.show(defaultTenant);
        this.loginDialogVisible=true;
        return this;
    }

    public String getUserName() {
        return super.getUserName();
    }

    public String getPassword() {
        return super.getPassword();
    }


    public String getTenant() {
        return super.getTenant();
    }

    public void clickLogin() {
        loginHandler.handle(super.makeLoginCredentials());
    }

    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setTenant(String tenant) {
        super.setTenant(tenant);
    }

    @Override
    public void addLoginHandler(LoginHandler loginHandler) {
        super.addLoginHandler(loginHandler);
        this.loginHandler=loginHandler;
    }

    public MaterialTextBox getPasswordField(){
        return super.getPasswordField();
    }

    public MaterialTextBox getUserNameField(){
        return super.getUserNameField();
    }

    public MaterialTextBox getTenantField(){
        return super.getTenantField();
    }
}
