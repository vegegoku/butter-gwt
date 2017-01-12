package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.*;

public class NamedFakeChainProvider extends FakeAuthenticationProvider {
    private final String name;

    public NamedFakeChainProvider(String name, AuthenticationContext context) {
        super(context);
        this.name = name;
    }

    @Override
    public void begin() {
        super.begin();
    }
}
