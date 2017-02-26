package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.client.annotations.AutoRequest;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.annotation.processor.client.ContributionPresenterInterface;

@Contribute
@AutoRequest(presenters={ContributionPresenterInterface.class}, method="onMainContextReceived")
public class AnnotatedClassWithPresentableContribution implements Contribution<MainExtensionPoint> {

    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
    }
}