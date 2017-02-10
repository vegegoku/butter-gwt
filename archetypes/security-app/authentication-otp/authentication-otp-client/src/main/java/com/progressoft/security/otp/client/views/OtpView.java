package com.progressoft.security.otp.client.views;

import com.progressoft.security.otp.client.presenters.VerifyHandler;
import org.akab.engine.core.api.client.mvp.view.View;

public interface OtpView extends View {
    OtpView show();

    void addVerifyHandler(VerifyHandler handler);

    void invalidateField();

    void showErrorMessage(String errorMessage);
}