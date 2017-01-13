package com.progressoft.security.login.shared.extension;

public class ViewLoginCredentials implements LoginCredentials{

    private String userName;
    private String password;
    private String tenant;

    public ViewLoginCredentials() {
    }

    public ViewLoginCredentials(String userName, String password, String tenant) {
        this.userName = userName;
        this.password = password;
        this.tenant = tenant;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
