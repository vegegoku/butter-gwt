package com.progressoft.security.sample.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import com.progressoft.security.sample.client.requests.SampleSampleClientRequest;

@Contribute
public class SampleContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new SampleSampleClientRequest(extensionPoint).send();
    }
}
