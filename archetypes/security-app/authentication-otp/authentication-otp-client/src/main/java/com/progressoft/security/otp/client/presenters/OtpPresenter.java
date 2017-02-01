package com.progressoft.security.otp.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface OtpPresenter extends Presentable{
    void onAuthenticationContextRecieved(AuthenticationContext context);

    void beginOtpAuthentication();

    void onOtpCodeGenerated();
}