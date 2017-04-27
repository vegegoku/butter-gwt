package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Request
public class ObtainMainExtensionPointForInjectContributionPresenterInterfaceClientRequest extends ClientRequest<InjectContributionPresenterInterface>{

    private MainExtensionPoint extensionPoint;

    public ObtainMainExtensionPointForInjectContributionPresenterInterfaceClientRequest(MainExtensionPoint extensionPoint){

        this.extensionPoint=extensionPoint;
    }

    @Override
    protected void process(InjectContributionPresenterInterface presenter){

        presenter.onMainExtensionPointContextReceived(extensionPoint.context());
    }
}