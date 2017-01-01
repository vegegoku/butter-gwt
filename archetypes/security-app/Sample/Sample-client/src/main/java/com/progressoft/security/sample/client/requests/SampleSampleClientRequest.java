package com.progressoft.security.sample.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import com.progressoft.security.sample.client.presenters.SamplePresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import org.akab.engine.core.api.client.annotations.Request;
import com.progressoft.security.sample.client.presenters.SamplePresenter;

@Request
public class SampleSampleClientRequest extends ClientRequest<SamplePresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public SampleSampleClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(SamplePresenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint, "Hello world! from Sample contribution request");
    }
}