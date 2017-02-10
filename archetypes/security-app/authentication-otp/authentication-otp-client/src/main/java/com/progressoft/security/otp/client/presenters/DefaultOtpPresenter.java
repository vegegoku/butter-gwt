package com.progressoft.security.otp.client.presenters;

import com.google.gwt.user.client.Window;
import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.otp.client.provider.OtpClientAuthenticationProvider;
import com.progressoft.security.otp.client.requests.GenerateOtpCodeServerRequest;
import com.progressoft.security.otp.client.requests.VerifyOtpCodeServerRequest;
import com.progressoft.security.otp.client.views.OtpView;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;

import static java.util.Objects.isNull;

@Presenter
public class DefaultOtpPresenter extends BaseClientPresenter<OtpView> implements OtpPresenter {

    private AuthenticationContext authenticationContext;
    private AuthenticationLayoutContext authenticationLayoutContext;

    @Override
    public void initView(OtpView view) {
        view.addVerifyHandler(otpCode -> {
            if (isNull(otpCode) || otpCode.trim().isEmpty())
                view.invalidateField();
            else
                new VerifyOtpCodeServerRequest(otpCode).send();
        });
    }

    @Override
    public void onAuthenticationLayoutInitialized(AuthenticationLayoutContext context) {
        this.authenticationLayoutContext = context;
    }

    @Override
    public void showErrorMessage() {
        view.showErrorMessage("Invalid OTP code");
    }

    @Override
    public void onChainCompletedSuccessfully(Principal principal) {
        Window.alert("success");
        authenticationContext.onChainCompleted(() -> principal);
    }

    @Override
    public void onAuthenticationContextRecieved(AuthenticationContext context) {
        this.authenticationContext = context;
        this.authenticationContext.registerProvider(OtpClientAuthenticationProvider.OTP, new OtpClientAuthenticationProvider());
    }

    @Override
    public void beginOtpAuthentication() {
        new GenerateOtpCodeServerRequest().send();
    }

    @Override
    public void onOtpCodeGenerated() {
        this.authenticationLayoutContext.showViewInMainPanel(view.show());
    }
}