package com.progressoft.security.login.server.builder;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.login.shared.LoginPrincipal;
import com.progressoft.security.model.user.PrincipalBuilder;

import java.util.Deque;

public class LoginPrincipalBuilder implements PrincipalBuilder {

    private String userName;
    private String tenant;
    private Deque<String> chains;

    @Override
    public PrincipalBuilder name(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public PrincipalBuilder tenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    @Override
    public PrincipalBuilder chains(Deque<String> chains) {
        this.chains = chains;
        return this;
    }

    @Override
    public Principal build() {
        return new LoginPrincipal(userName, tenant, chains);
    }
}
