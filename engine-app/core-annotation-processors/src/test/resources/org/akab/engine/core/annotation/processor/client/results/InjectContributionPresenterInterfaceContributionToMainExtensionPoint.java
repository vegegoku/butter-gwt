package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.client.annotations.AutoRequest;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.annotation.processor.client.InjectContributionPresenterInterface;
import org.akab.engine.core.annotation.processor.client.ObtainMainExtensionPointForInjectContributionPresenterInterfaceClientRequest;

@Contribute
@AutoRequest(presenters={InjectContributionPresenterInterface.class}, method="onMainExtensionPointContextReceived")
public class InjectContributionPresenterInterfaceContributionToMainExtensionPoint implements Contribution<MainExtensionPoint> {

    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new ObtainMainExtensionPointForInjectContributionPresenterInterfaceClientRequest(extensionPoint).send();
    }
}