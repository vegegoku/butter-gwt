package com.progressoft.security.login.shared;

import com.progressoft.security.authentication.shared.extension.Principal;

public class LoginPrincipal implements Principal {

    private String userName;
    private String tenant;

    private LoginPrincipal() {
    }

    public LoginPrincipal(String userName, String tenant){
        this.userName = userName;
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
    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        return tenant.equals(((LoginPrincipal)other).tenant) && userName.equals(((LoginPrincipal)other).userName);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + tenant.hashCode();
        return result;
    }
}
