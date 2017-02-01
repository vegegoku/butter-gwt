package com.progressoft.security.authentication.client.extensions;

import com.progressoft.security.authentication.shared.extension.RootChainCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.RootChainContext;

public class DefaultRootChainExtensionPoint implements RootChainCompletedExtensionPoint {

    private final RootChainContext context;

    public DefaultRootChainExtensionPoint(RootChainContext context) {
        this.context = context;
    }

    @Override
    public RootChainContext context() {
        return this.context;
    }
}
