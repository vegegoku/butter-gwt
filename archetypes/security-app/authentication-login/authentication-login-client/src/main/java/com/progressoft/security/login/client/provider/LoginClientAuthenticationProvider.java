package com.progressoft.security.login.client.provider;

import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;
import com.progressoft.security.login.client.requests.FindDefaultTenantServerRequest;

public class LoginClientAuthenticationProvider implements ClientAuthenticationProvider{

    public static final String LOGIN="LOGIN";

    @Override
    public void begin() {
        new FindDefaultTenantServerRequest().send();
    }
}
