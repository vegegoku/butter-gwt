package com.progressoft.security.otp.client.presenters;

import com.google.gwt.user.client.Window;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.otp.client.provider.OtpClientAuthenticationProvider;
import com.progressoft.security.otp.client.requests.GenerateOtpCodeServerRequest;
import com.progressoft.security.otp.client.views.OtpView;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;

@Presenter
public class DefaultOtpPresenter extends BaseClientPresenter<OtpView> implements OtpPresenter {

    private AuthenticationContext authenticationContext;

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        this.authenticationContext = context;
        context.registerProvider(OtpClientAuthenticationProvider.OTP, new OtpClientAuthenticationProvider());
    }

    @Override
    public void beginOtpAuthentication() {
        new GenerateOtpCodeServerRequest().send();
    }

    @Override
    public void onOtpCodeGenerated() {
        Window.alert("show otp");
        view.show();
    }
}