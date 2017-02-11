package com.progressoft.security.otp.client;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.otp.client.presenters.DefaultOtpPresenter;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class OtpPresenterSpy extends DefaultOtpPresenter{

    private AuthenticationContext context;
    public boolean otpCodeGenerated=false;
    public String errorDescription;
    public boolean errorMessageShown;


    @Override
    protected String getConcrete() {
        return DefaultOtpPresenter.class.getCanonicalName();
    }


    public FakeAuthenticationContext getContext() {
        return (FakeAuthenticationContext)context;
    }

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        super.onAuthenticationContextRecieved(context);
        this.context=context;
    }

    @Override
    public void onOtpCodeGenerated() {
        super.onOtpCodeGenerated();
        this.otpCodeGenerated=true;
    }

    @Override
    public void showErrorMessage(String message, String details) {
        super.showErrorMessage(message, details);
        this.errorDescription=details;
        this.errorMessageShown=true;
    }
}
