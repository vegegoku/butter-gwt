package com.progressoft.security.otp.client.contributions;

import com.progressoft.security.authentication.shared.extension.RootChainCompletedExtensionPoint;
import com.progressoft.security.otp.client.requests.ObtainPrincipalFromRootChainClientRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

@Contribute
public class OtpToRootChainContribution implements Contribution<RootChainCompletedExtensionPoint>{
    @Override
    public void contribute(RootChainCompletedExtensionPoint extensionPoint) {
        new ObtainPrincipalFromRootChainClientRequest(extensionPoint.context().principal()).send();
    }
}
