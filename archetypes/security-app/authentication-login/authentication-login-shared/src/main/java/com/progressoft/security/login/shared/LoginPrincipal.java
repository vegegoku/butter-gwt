package com.progressoft.security.login.shared;

import com.progressoft.security.authentication.shared.extension.Principal;

import java.util.Deque;

public class LoginPrincipal implements Principal {

    private String userName;
    private String tenant;
    private Deque<String> chains;

    private LoginPrincipal() {
    }

    public LoginPrincipal(String userName, String tenant, Deque<String> chains) {
        this.userName = userName;
        this.tenant = tenant;
        this.chains = chains;
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
    public Deque<String> chains() {
        return chains;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        return tenant.equals(((LoginPrincipal) other).tenant) && userName.equals(((LoginPrincipal) other).userName);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + tenant.hashCode();
        return result;
    }
}
