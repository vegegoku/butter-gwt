package com.progressoft.security.otp.client.presenters;

import com.progressoft.security.authentication.shared.extension.AuthenticationContext;
import com.progressoft.security.authentication.shared.extension.FailedChainContext;
import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.layout.shared.extension.AuthenticationLayoutContext;
import com.progressoft.security.uimessages.shared.extension.UiMessagesContext;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface OtpPresenter extends Presentable{
    void onAuthenticationContextRecieved(AuthenticationContext context);

    void beginOtpAuthentication();

    void onOtpCodeGenerated();

    void onAuthenticationLayoutInitialized(AuthenticationLayoutContext context);

    void showErrorMessage(String message, String details);

    void onChainCompletedSuccessfully(Principal principal);

    void onUiMessagesContextRecieved(UiMessagesContext uiMessagesContext);

    void onOtpFailed(FailedChainContext context);
}