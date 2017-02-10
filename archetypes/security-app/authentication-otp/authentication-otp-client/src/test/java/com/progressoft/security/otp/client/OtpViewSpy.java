package com.progressoft.security.otp.client;

import com.progressoft.security.otp.client.views.DefaultOtpView;
import com.progressoft.security.otp.client.views.OtpView;

public class OtpViewSpy extends DefaultOtpView {

    private boolean otpDialogVisible = false;
    private boolean otpFieldInvalid = false;
    private boolean errorMessageShowed = false;

    public boolean isOtpDialogVisible() {
        return otpDialogVisible;
    }

    @Override
    public OtpView show() {
        super.show();
        this.otpDialogVisible = true;
        return this;
    }

    @Override
    public String toString() {
        return "i am the view spy.";
    }

    @Override
    public void invalidateField() {
        super.invalidateField();
        otpFieldInvalid = true;
    }

    boolean isOtpFieldInvalid() {
        return otpFieldInvalid;
    }

    void clickVerify() {
        this.handler.handle(getOtpCode());
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        super.showErrorMessage(errorMessage);
        this.errorMessageShowed = true;
    }

    public boolean isErrorMessageShowed() {
        return errorMessageShowed;
    }
}
