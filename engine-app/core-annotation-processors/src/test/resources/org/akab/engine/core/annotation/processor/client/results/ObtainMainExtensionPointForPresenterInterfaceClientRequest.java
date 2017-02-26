package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.annotation.processor.client.ContributionPresenterInterface;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Request
public class ObtainMainExtensionPointForContributionPresenterInterfaceClientRequest extends ClientRequest<ContributionPresenterInterface>{

    private MainExtensionPoint extensionPoint;

    public ObtainMainExtensionPointForPresenterInterfaceClientRequest(MainExtensionPoint extensionPoint){

        this.extensionPoint=extensionPoint;
    }

    @Override
    protected void process(ContributionPresenterInterface presenter){

        presenter.onMainContextReceived(extensionPoint.context());
    }
}