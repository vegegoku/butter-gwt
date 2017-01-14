package com.progressoft.security.login.server.model;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.login.shared.LoginPrincipal;

import java.util.Objects;

public class User {
    private final String userName;
    private final String secret;
    private final String tenant;

    public User(String userName, String secret, String tenant) {
        this.userName = userName;
        this.secret = secret;
        this.tenant = tenant;
    }

    public boolean isSamePassword(String password) {
        return Objects.equals(password ,secret);
    }

    public Principal makePrincipal(){
        return new LoginPrincipal(userName, tenant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (secret != null ? !secret.equals(user.secret) : user.secret != null) return false;
        return tenant != null ? tenant.equals(user.tenant) : user.tenant == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (tenant != null ? tenant.hashCode() : 0);
        return result;
    }
}
