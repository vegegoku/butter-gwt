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
}
