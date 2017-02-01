package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.RootChainCompletedExtensionPoint;
import com.progressoft.security.authentication.shared.extension.RootChainContext;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class FakeRootChainContribution implements Contribution<RootChainCompletedExtensionPoint>{

    private RootChainContext context;

    @Override
    public void contribute(RootChainCompletedExtensionPoint extensionPoint) {
        context=extensionPoint.context();
    }

    public RootChainContext getContext() {
        return context;
    }
}
