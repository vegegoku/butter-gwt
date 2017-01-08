package com.progressoft.security.authentication.client;

import com.progressoft.security.authentication.client.presenters.DefaultAuthenticationPresenter;
import com.progressoft.security.authentication.shared.extension.CompletedChainContext;
import com.progressoft.security.authentication.shared.extension.FailedChainContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class AuthenticationPresenterSpy extends DefaultAuthenticationPresenter {

    private boolean contributionCompleted;
    private String rootChain;
    private boolean fakeLoginChainCompletedSuccessfully;
    private CompletedChainContext rootChainSuccessContext;
    private FailedChainContext failedChainContext;
    private boolean fakeLoginChainFailed;
    private int authenticationCallCounter=0;

    public boolean isContributionCompleted() {
        return contributionCompleted;
    }

    @Override
    public void startAuthentication(String rootChain){
        super.startAuthentication(rootChain);
        this.rootChain=rootChain;
    }

    @Override
    protected String getConcrete() {
        return DefaultAuthenticationPresenter.class.getCanonicalName();
    }

    public String getRootChain() {
        return rootChain;
    }

    @Override
    public void onChainCompletedSuccessfully(CompletedChainContext context) {
        super.onChainCompletedSuccessfully(context);
        fakeLoginChainCompletedSuccessfully = true;
        this.rootChainSuccessContext=context;
    }

    @Override
    public void onChainFailed(FailedChainContext failedChainContext) {
        super.onChainFailed(failedChainContext);
        this.fakeLoginChainFailed=true;
        this.failedChainContext=failedChainContext;
    }

    public CompletedChainContext getRootChainSuccessContext() {
        return rootChainSuccessContext;
    }

    public FailedChainContext getFailedChainContext() {
        return failedChainContext;
    }

    @Override
    public void authenticate() {
        super.authenticate();
        authenticationCallCounter++;
    }

    public int getAuthenticationCallCounter() {
        return authenticationCallCounter;
    }

    public boolean getFakeLoginChainCompletedSuccessfully() {
        return fakeLoginChainCompletedSuccessfully;
    }

    public boolean getFakeLoginChainFailed() {
        return fakeLoginChainFailed;
    }
}
