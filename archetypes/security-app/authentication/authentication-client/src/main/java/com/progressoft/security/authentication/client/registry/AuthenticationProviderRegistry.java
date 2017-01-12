package com.progressoft.security.authentication.client.registry;

import com.progressoft.security.authentication.shared.extension.ClientAuthenticationProvider;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationProviderRegistry {

    private static final Map<String, ClientAuthenticationProvider> providers = new HashMap<>();

    private AuthenticationProviderRegistry() {
    }

    public static void registerProvider(String key, ClientAuthenticationProvider provider) {
        AuthenticationProviderRegistry.providers.put(key, provider);
    }

    public static ClientAuthenticationProvider get(String name) {
        return providers.get(name);
    }
}
