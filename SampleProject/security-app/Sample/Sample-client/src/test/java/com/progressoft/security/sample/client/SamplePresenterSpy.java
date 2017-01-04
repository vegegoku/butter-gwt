package com.progressoft.security.sample.client;

import com.progressoft.security.sample.client.presenters.DefaultSamplePresenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class SamplePresenterSpy extends DefaultSamplePresenter{

    private boolean contributionCompleted;
    private String message;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        super.contributeToMainModule(mainExtensionPoint, welcomeMessage);
        this.contributionCompleted=true;
    }

    public boolean isContributionCompleted() {
        return contributionCompleted;
    }

    @Override
    protected String getConcrete() {
        return DefaultSamplePresenter.class.getCanonicalName();
    }

    @Override
    public void setResponse(String message) {
        super.setResponse(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
