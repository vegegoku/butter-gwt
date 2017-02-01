package com.progressoft.security.otp.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.otp.client.provider.OtpClientAuthenticationProvider;
import com.progressoft.security.otp.client.requests.GenerateOtpCodeServerRequest;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.otp.client.views.OtpView;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import java.util.Objects;

import static java.util.Objects.*;

@Presenter
public class DefaultOtpPresenter extends BaseClientPresenter<OtpView> implements OtpPresenter {

    private AuthenticationContext authenticationContext;

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        this.authenticationContext=context;
        context.registerProvider(OtpClientAuthenticationProvider.OTP, new OtpClientAuthenticationProvider());
    }

    @Override
    public void beginOtpAuthentication() {
        new GenerateOtpCodeServerRequest().send();
    }

    @Override
    public void onOtpCodeGenerated() {
        view.show();
    }
}