package com.progressoft.security.login.client;

import com.google.gwt.user.client.ui.HasText;
import com.progressoft.security.login.client.presenters.LoginHandler;
import com.progressoft.security.login.client.views.DefaultLoginView;
import gwt.material.design.client.ui.MaterialTextBox;

public class LoginViewSpy extends DefaultLoginView {

    private boolean loginDialogVisible=false;
    private String user;
    private String pass;
    private LoginHandler loginHandler;

    public boolean isLoginDialogVisible() {
        return loginDialogVisible;
    }

    public void setValues(String user, String password){
        this.user=user;
        this.pass =password;
    }

    @Override
    public void show(String defaultTenant) {
        super.show(defaultTenant);
        this.loginDialogVisible=true;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
